package com.example.demo1.repositories;

import com.example.demo1.models.OrderDetails;
import com.example.demo1.models.Orders;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface
OrderDetailsRepository extends CrudRepository<OrderDetails, Long> {
    Optional<OrderDetails> findById(Long id);

    List<OrderDetails> findByOrders(Orders orders);


}
