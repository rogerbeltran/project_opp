package com.ups.oop.service;

import com.ups.oop.dto.PaymentMethDTO;
import com.ups.oop.entity.PaymentMeth;
import com.ups.oop.repository.PaymentMethRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentMethService {
    private final PaymentMethRepository PaymentMethRepository;
    private List<PaymentMethDTO> paymentDTOList = new ArrayList<>();

    public PaymentMethService(PaymentMethRepository paymentMethRepository, List<PaymentMethDTO> paymentDTOList) {
        PaymentMethRepository = paymentMethRepository;
        this.paymentDTOList = paymentDTOList;
    }

    public ResponseEntity createPayment(PaymentMethDTO paymentMethDTO) {
        String paymentMethId = paymentMethDTO.getId();
        Optional<PaymentMeth> paymentOptional = PaymentMethRepository.findById(Long.valueOf(paymentMethId));
        if(paymentOptional.isPresent()) {
            String errorMessage = "Payment method with id " + paymentMethId + " already exists";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        } else {
            //Before Register Person, name and lastname are present
            if(paymentMethDTO.getId().contains(" ")) {
                //Build Person and save in Repository
                PaymentMeth paymentmethRecord = new PaymentMeth();
                paymentmethRecord.setId(paymentmethRecord.getId());
                String[] nameStrings = paymentMethDTO.getMethod().split(" ");
                String name = nameStrings[0];
                paymentmethRecord.setMethod(name);
                PaymentMethRepository.save(paymentmethRecord);
                return ResponseEntity.status(HttpStatus.OK).body(paymentMethDTO);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Employee name must contain two strings separated by a whitespace");
            }
        }
    }

    public ResponseEntity getAllPayment() {
        List<PaymentMethDTO> paymentList = getPayment();
        if(paymentList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product List not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(paymentList);
    }

    public List<PaymentMethDTO> getPayment() {
        Iterable<PaymentMeth> paymentMethIterable = PaymentMethRepository.findAll();
        List<PaymentMethDTO> paymentList = new ArrayList<>();
        for(PaymentMeth pay : paymentMethIterable) {
            PaymentMethDTO paymentmeth = new PaymentMethDTO();
            paymentmeth.setId(String.valueOf(pay.getId()));
            paymentmeth.setMethod(pay.getMethod());
            paymentList.add(paymentmeth);
        }
        return paymentList;
    }

    public ResponseEntity getPaymentMethbyId(String paymentId) {
        Optional<PaymentMeth> paymentOptional = PaymentMethRepository.findById(Long.valueOf(paymentId));
        if(paymentOptional.isPresent()) {
            //if record was found
            PaymentMeth paymentFound = paymentOptional.get();
            PaymentMeth paymentmeth = new PaymentMeth(paymentFound.getId(), paymentFound.getMethod());
            return ResponseEntity.status(HttpStatus.OK).body(paymentmeth);
        } else {
            //if record wasn't found
            String errorMessage = "Payment Method with id " + paymentId + " not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    public ResponseEntity updatePayment(PaymentMethDTO paymentMethDTO) {
        String paymentId = paymentMethDTO.getId();
        //check repository if record exist
        Optional<PaymentMeth> paymentMethOptional = PaymentMethRepository.findById(Long.valueOf(paymentId));
        if(paymentMethOptional.isPresent()) {
            //If record exists, then perform Update
            PaymentMeth paymentMeth = paymentMethOptional.get();
            if(paymentMethDTO.getId().contains(" ")) {
                //Build Person and save in Repository
                paymentMeth.setId(Long.valueOf(paymentId));
                String[] nameStrings = paymentMethDTO.getMethod().split(" ");
                String name = nameStrings[0];
                paymentMeth.setMethod(name);
                PaymentMethRepository.save(paymentMeth);
                return ResponseEntity.status(HttpStatus.OK).body(paymentMethDTO);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Payment Method name must contain two strings separated by a whitespace");
            }
        } else {
            String errorMessage = "Payment Method with id " + paymentId + " not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    public ResponseEntity deletePaymentById(String id) {
        String message = "Payment Method with id " + id;
        Optional<PaymentMeth> paymentMethOptional = PaymentMethRepository.findById(Long.valueOf(id));
        if(paymentMethOptional.isPresent()) {
            //If record was found, then delete record
            PaymentMethRepository.delete(paymentMethOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body(message + " removed successfully");
        } else {
            //Return error message
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message + " not found");
        }
    }
}