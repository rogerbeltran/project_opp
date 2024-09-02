package com.ups.oop.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter

public class PaymentMethodDTO {
    private String id;
    private String method;
}
