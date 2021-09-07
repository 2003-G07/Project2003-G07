package com.example.demo1.repositories;

import com.example.demo1.models.Customer;
import com.example.demo1.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{


}
