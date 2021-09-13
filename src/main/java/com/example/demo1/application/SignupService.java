package com.example.demo1.application;

import com.example.demo1.models.Customer;
import com.example.demo1.models.Role;
import com.example.demo1.models.User;
import com.example.demo1.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service @Slf4j
public class SignupService {

    private UserRepository userRepository;


    public SignupService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }




    public void signup(final Customer customer,final String username, final String password, final List<Role> roles) {
        log.info("Signing up user {}", username);
        final User user = new User(customer, null,username, password, roles);
        userRepository.save(user);
    }
}
