package com.ups.oop.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter

public class DetailsDTO {
    private String id;
    private String receipt;
    private String product;
    private int unit_price;
    private int    quantity;
}
