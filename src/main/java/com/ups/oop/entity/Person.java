
package com.ups.oop.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String personId;
    private String name;
    private String lastName;
    private Integer age;

    public Person(Integer age, String lastName, String name, String personId) {
        this.age = age;
        this.lastName = lastName;
        this.name = name;
        this.personId = personId;
    }
}
