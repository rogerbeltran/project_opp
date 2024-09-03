package com.ups.oop.service;

import com.ups.oop.dto.ReceiptDTO;
import com.ups.oop.entity.Receipt;
import com.ups.oop.repository.ReceiptRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReceiptService {

    private final ReceiptRepository receiptRepository;

    public ReceiptService(ReceiptRepository receiptRepository) {
        this.receiptRepository = receiptRepository;
    }

    public List<ReceiptDTO> getReceipts() {
        Iterable<Receipt> receiptIterable = receiptRepository.findAll();
        List<ReceiptDTO> receiptList = new ArrayList<>();

        for (Receipt receipt : receiptIterable) {
            ReceiptDTO receiptDTO = new ReceiptDTO();
            receiptDTO.setId(receipt.getId().toString());
            receiptDTO.setSerial(receipt.getSerial());
            receiptDTO.setClient(receipt.getClient().getName() + " " +receipt.getClient().getLastName());
            receiptDTO.setDate(receipt.getReceiptDate());
            receiptDTO.setTotal_price(receipt.getTotal_price().doubleValue());
            receiptDTO.setWorker(receipt.getWorker().getName() + " " + receipt.getWorker().getLastName());
            receiptDTO.setPayment_method(receipt.getPaymentMeth().getMethod());

            receiptList.add(receiptDTO);
        }

        return receiptList;
    }
}
