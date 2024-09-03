package com.ups.oop.repository;

import com.ups.oop.entity.Worker;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface WorkerRepository extends CrudRepository<Worker, Long> {
}
