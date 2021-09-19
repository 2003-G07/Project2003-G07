package com.example.demo1.application;

import com.example.demo1.models.Customer;
import com.example.demo1.models.Role;
import com.example.demo1.models.User;
import com.example.demo1.repositories.RoleRepository;
import com.example.demo1.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @Slf4j
public class SignupService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public SignupService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }




    public void signup(final String firstName, final String lastName, final String tel, final String email, final String username, final String password) {
        log.info("Signing up user {}", username);

        Role customerRole = roleRepository.findByName("Customer");
        if(customerRole==null){
            throw new IllegalStateException("There is no Customer Role configured in the database, cannot create user.");
        }
        Customer customer = new Customer(firstName, lastName, tel, email);
        final User user = new User(customer, null,username, password, List.of(customerRole));
        userRepository.save(user);
    }
}
