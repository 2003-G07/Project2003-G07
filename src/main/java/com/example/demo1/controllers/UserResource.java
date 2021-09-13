package com.example.demo1.controllers;

import com.example.demo1.application.SignupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

@RestController @Slf4j
public class UserResource {
    private final SignupService signupService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserResource(SignupService signupService, PasswordEncoder passwordEncoder) {
        this.signupService = signupService;
        this.passwordEncoder = passwordEncoder;
    }
}

