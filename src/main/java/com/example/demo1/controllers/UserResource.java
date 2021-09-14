package com.example.demo1.controllers;

import com.example.demo1.api.SignUpRequestDto;
import com.example.demo1.application.SignupService;
import com.example.demo1.models.CheckOutForm;
import com.example.demo1.models.SignupForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController @Slf4j
public class UserResource {
    private final SignupService signupService;
   private final BCryptPasswordEncoder passwordEncoder;

   @Autowired
  public UserResource(SignupService signupService, BCryptPasswordEncoder passwordEncoder) {
        this.signupService = signupService;
        this.passwordEncoder = passwordEncoder;
    }
    /*@PostMapping("/user/signup")
    public String signUp(@RequestBody SignUpRequestDto signUpRequest) {
        log.info("Creating user {}", signUpRequest.getUsername());
        signupService.signup( signUpRequest.getCustomer(),signUpRequest.getUsername(), passwordEncoder.encode(signUpRequest.getPassword()), signUpRequest.getRoles());
        return "Successfully logged in";
    }*/
    @GetMapping("user/signupRegister.html")
    public String showSignupRegisterForm(SignupForm signupForm) {

        return "user/signupRegister";
    }

    @PostMapping("/user/signup")
    public String submitSignupForm(@Valid SignupForm signupForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "user/signupRegister.html";
        }
        //checkOutFormGlobal = checkOutForm;

        return "redirect:/inloggning/inloggningsida.html";
    }
}

