package com.example.demo1;


import com.example.demo1.models.Customer;
import com.example.demo1.models.Produkt;
import com.example.demo1.repositories.CustomerRepository;
import com.example.demo1.repositories.ProduktRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel Bojic
 * Date: 2021-04-13
 * Time: 15:45
 * Project: Project2003-G07
 * Copyright: MIT
 */
@Controller
public class hej {

    @Autowired
    private CustomerRepository customerRepository;

   @Autowired
   private ProduktRepository produktRepository;


    @GetMapping("/greeting")
    public String greetingForm(@RequestParam String firstNa, String lastNa,String teleNr , String emailAd , Model model) throws SQLException {

        model.addAttribute("firstNa",firstNa);
        model.addAttribute("lastNa",lastNa);
        model.addAttribute("teleNr",teleNr);
        model.addAttribute("emailAd",emailAd);


        return "customers";
    }


    @GetMapping("/register")
    public String showForm(Model model){
        System.out.println("register funkar");


        return "regiser_form";
    }

   @PostMapping("/register")
   public String submitForm(@ModelAttribute("customer") Customer customer){
       System.out.println(customer);
       return "register_success";
   }





    @GetMapping("admin/addKund")
    public ModelAndView addCustomer() throws SQLException {
        System.out.println("det funkar");

        ModelAndView modelAndView = new ModelAndView();


        return modelAndView;
    }

    @GetMapping("/index.html")
    public ModelAndView listCustomers() throws SQLException {
        System.out.println("det funkar");

        ModelAndView modelAndView = new ModelAndView();


        produktRepository.save(new Produkt("Falun Gong Earl Grey",11,"https://picsum.photos/500?random=1","Ett svart te smaksatt med bergamott.",160,"Dryck"));




        modelAndView.addObject("listCustomers", produktRepository.findAll());



        return modelAndView;
    }



}