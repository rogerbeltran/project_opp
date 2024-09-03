package com.ups.oop.entity;

import com.ups.oop.dto.Person;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Worker extends Person {
    private String workerCode;
    @OneToMany(mappedBy = "worker")
    private List<Receipt> receipts = new ArrayList<>();

    public Worker() {
        super();
    }

    public Worker(String workerCode, String personId, String name, String lastname, Integer age) {
        super(age, lastname, name, personId);
        this.workerCode = workerCode;
    }
}