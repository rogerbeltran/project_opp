package com.ups.oop.repository;

import com.ups.oop.entity.Distributor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DistributorRepository extends CrudRepository<Distributor, Long> {
}
