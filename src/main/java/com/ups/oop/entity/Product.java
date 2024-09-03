package com.ups.oop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String productId;
    private String name;
    private String supplierss;
    private double price;
    @ManyToOne
    @JoinColumn(name = "distributor_id", nullable = true)
    private Distributor distributor;
}