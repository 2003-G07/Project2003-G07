package com.example.demo1.repositories;

import com.example.demo1.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Long>{
   // User save(final User user);
 //  Optional<User> findByUsername(String username);
    User findByUsername(String username);
}
