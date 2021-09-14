package com.example.demo1.controllers;

import com.example.demo1.api.SignUpRequestDto;
import com.example.demo1.application.SignupService;
import com.example.demo1.models.CheckOutForm;
import com.example.demo1.models.SignupForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class UserResource {
    private final SignupService signupService;
   private final BCryptPasswordEncoder passwordEncoder;

    SignupForm SignupFormGlobal;

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
    public String showSignupRegisterForm(SignupForm signupForm, Model model) {
        if(signupForm == null)
           model.addAttribute("SignupForm", new SignupForm());
        else
            model.addAttribute("SignupForm", signupForm);
        return "user/signupRegister.html";
    }


   @PostMapping("/user/submitSignupForm")
   public String submitsignupForm(@Valid SignupForm signupForm, BindingResult bindingResult) {

       if (bindingResult.hasErrors()) {
           return "user/SignupRegister.html";
       }
       SignupFormGlobal = signupForm;
       System.out.println("fel");

       return "redirect:/inloggning/inloggningsida.html";
   }
}

