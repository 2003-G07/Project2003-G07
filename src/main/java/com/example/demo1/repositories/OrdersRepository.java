package com.example.demo1.repositories;

import com.example.demo1.models.Customer;
import com.example.demo1.models.Orders;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Henrik Elofsson
 * Date: 2021-04-16
 * Time: 14:36
 * Project: Project2003-G07
 * Copyright: MIT
 */
public interface OrdersRepository extends CrudRepository<Orders, Long> {

    List<Orders> findByAddressId(String id);

}
