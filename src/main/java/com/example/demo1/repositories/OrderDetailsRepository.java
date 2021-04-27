package com.example.demo1.repositories;

import com.example.demo1.models.OrderDetails;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface
OrderDetailsRepository extends CrudRepository<OrderDetails, Long> {
    List<OrderDetails> findById(String id);
}
