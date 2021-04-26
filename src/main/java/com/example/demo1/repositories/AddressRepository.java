package com.example.demo1.repositories;

import com.example.demo1.models.Address;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by Henrik Elofsson
 * Date: 2021-04-16
 * Time: 14:14
 * Project: Project2003-G07
 * Copyright: MIT
 */
public interface AddressRepository extends CrudRepository<Address, Long> {

    Optional<Address> findById(Long id);

}