package com.ups.oop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String branch_name;
    private String store_name;
}