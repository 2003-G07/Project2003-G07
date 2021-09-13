package com.example.demo1.repositories;

import com.example.demo1.models.Orders;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdersRepository extends CrudRepository<Orders, Long> {
    List<Orders> findById(String id);

    List<Orders> findByStatus(int status);


}
