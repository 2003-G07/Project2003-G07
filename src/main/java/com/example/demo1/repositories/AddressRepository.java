package com.example.demo1.repositories;

import com.example.demo1.models.Address;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AddressRepository extends CrudRepository<Address, Long> {
    Optional<Address> findById(Long id);
}