package com.example.demo1.repositories;

import com.example.demo1.models.Orders;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrdersRepository extends CrudRepository<Orders, Long> {
    List<Orders> findById(String id);
    List<Orders> findByStatus(int status);

}
