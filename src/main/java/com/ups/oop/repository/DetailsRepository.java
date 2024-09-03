package com.ups.oop.repository;

import com.ups.oop.entity.Details;
import org.springframework.data.repository.CrudRepository;

public interface DetailsRepository extends CrudRepository<Details, Long> {
}
