package com.example.demo1.repositories;

import com.example.demo1.models.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Daniel Bojic
 * Date: 2021-04-15
 * Time: 13:08
 * Project: gr7java
 * Copyright: MIT
 */
public interface CustomerRepository extends CrudRepository <Customer, Long>{

    List<Customer> findByFirstName(String firstname);

}
