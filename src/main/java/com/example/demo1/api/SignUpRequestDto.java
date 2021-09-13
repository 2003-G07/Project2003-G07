package com.example.demo1.api;

import com.example.demo1.models.Customer;
import com.example.demo1.models.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class SignUpRequestDto {
    Customer customer;
    private final String username;
    private final String password;
    private final List<Role> roles;

    public SignUpRequestDto(Customer customer,String username, String password, List<Role> roles) {
        this.username = username;
        this.password = password;
        this.customer= customer;
        this.roles = List.copyOf(roles);
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public List<Role> getRoles() {
        return List.copyOf(roles);
    }
}

