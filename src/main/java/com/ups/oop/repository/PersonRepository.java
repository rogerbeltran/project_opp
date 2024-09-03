package com.ups.oop.repository;

import com.ups.oop.dto.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, Long> {
    Optional<Person> findByPersonId(String personId);
}