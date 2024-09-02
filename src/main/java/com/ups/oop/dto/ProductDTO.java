package com.ups.oop.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter

public class ProductDTO {
    private String product_description;
    private String productId;
    private String Id;
    private double unit_price;
    private List<String> distributor;
}