package com.ups.oop.repository;

import com.ups.oop.entity.Receipt;
import org.springframework.data.repository.CrudRepository;

public interface ReceiptRepository extends CrudRepository<Receipt, Long> {

}
