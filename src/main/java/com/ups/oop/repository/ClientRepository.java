package com.ups.oop.repository;

import com.ups.oop.dto.Person;
import com.ups.oop.entity.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client, Long> {


}
