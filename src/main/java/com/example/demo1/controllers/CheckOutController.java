package com.example.demo1.controllers;

import com.example.demo1.models.CheckOutForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CheckOutController {

    @GetMapping("varukorg/checkout.html")
    public String showCheckOutForm(CheckOutForm checkOutForm) {

        return "varukorg/checkout.html";
    }

}
