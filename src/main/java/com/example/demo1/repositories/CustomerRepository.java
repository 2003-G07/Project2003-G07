package com.example.demo1.repositories;

import com.example.demo1.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByFirstName(String firstname);

    List<Customer> findByFirstNameAndLastNameAndTelAndEmail(String firstname, String lastname, String tel, String email);

}
