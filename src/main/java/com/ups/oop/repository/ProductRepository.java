package com.ups.oop.repository;

import com.ups.oop.entity.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Optional<Product> findByProductId(String productId);
}
