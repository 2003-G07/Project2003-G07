package com.example.demo1.repositories;

import com.example.demo1.models.Customer;
import com.example.demo1.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository{
    void save(final User user);
   Optional<User> findByUsername(String username);

}
