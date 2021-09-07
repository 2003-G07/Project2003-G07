package com.example.demo1.repositories;

import com.example.demo1.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findById(Long id);

    Optional<Address> findByAddress(String address);

    List<Address> findByCityAndAddressAndZip(String city, String address, String zip);

}