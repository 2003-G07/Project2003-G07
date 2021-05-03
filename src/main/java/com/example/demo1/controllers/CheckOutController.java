package com.example.demo1.controllers;

import com.example.demo1.models.CheckOutForm;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class CheckOutController {

    @GetMapping("varukorg/checkout.html")
    public String showCheckOutForm(CheckOutForm checkOutForm) {

        return "varukorg/checkout.html";
    }


/*
    @PostMapping("/varukorg/submitcheckoutform")
    public String submitCheckOutForm(@Valid CheckOutForm checkOutForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "varukorg/checkout.html";
        }

        return "redirect:/varukorg/confirmedorder.html";
    }


 */


}
