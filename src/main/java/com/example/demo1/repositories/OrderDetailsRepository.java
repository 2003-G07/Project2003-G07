package com.example.demo1.repositories;

import com.example.demo1.models.OrderDetails;
import com.example.demo1.models.Orders;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Henrik Elofsson
 * Date: 2021-04-16
 * Time: 14:38
 * Project: Project2003-G07
 * Copyright: MIT
 */
public interface OrderDetailsRepository extends CrudRepository<OrderDetails, Long> {

    List<OrderDetails> findById(String id);

}
