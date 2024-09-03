package com.ups.oop.controller;

import com.ups.oop.dto.BranchDTO;
import com.ups.oop.dto.ClientDTO;
import com.ups.oop.dto.DistributorDTO;
import com.ups.oop.dto.PaymentMethDTO;
import com.ups.oop.service.BranchService;
import com.ups.oop.service.ClientService;
import com.ups.oop.service.DistributorService;
import com.ups.oop.service.PaymentMethService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {
    private  final PaymentMethService paymentMethService;

    public PaymentController(PaymentMethService paymentMethService) {
        this.paymentMethService = paymentMethService;
    }

    @PostMapping("/payment")
    public ResponseEntity createPayment(@RequestBody PaymentMethDTO paymentMethDTO){
        return this.paymentMethService.createPayment(paymentMethDTO);
    }

    @GetMapping("/get-all-payment")
    public ResponseEntity getAllPayment(){
        return this.paymentMethService.getAllPayment();
    }

    @GetMapping("/get-payment")
    public ResponseEntity getPaymentById(@RequestParam String id){
        return this.paymentMethService.getPaymentMethbyId(id);
    }

    @PutMapping("/update-payment")
    public ResponseEntity updatePayment(@RequestBody PaymentMethDTO paymentMethDTO){
        return this.paymentMethService.updatePayment(paymentMethDTO);
    }

    @DeleteMapping("/remove-payment")
    public ResponseEntity deletePayment(@RequestParam String id){
        return this.paymentMethService.deletePaymentById(id);
    }
}
