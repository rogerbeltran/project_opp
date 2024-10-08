package com.ups.oop.dto;


import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter

public class ReceiptDTO {
    private String id;
    private String serial;
    private String client;
    private Date   date;
    private Double total_price;
    private String worker;
    private String payment_method;
}
