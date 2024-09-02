package com.ups.oop.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class WorkerDTO {
    private String id;
    private String name;
    private int age;
    private String WorkerCode;
}