package com.example.demo1;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
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

    importSQLData data = new importSQLData();


    @RequestMapping("/")
    public String index() {
        return "hejsan!!";
    }

    @RequestMapping("")
    public String addKund() throws SQLException {
        data.connectToAndQueryDatabase("daniel", "daniel");
        data.findAllCustomer();
        List<Customer> shunos = data.getShunos();
        String temp = "Hello ";
        for (int i = 0; i < shunos.size(); i++) {
            temp += shunos.get(i).firstName + " ";
        }


        return temp;
    }

    @RequestMapping(value = "/kuk")
    public String saveCustomer() throws SQLException {
        data.connectToAndQueryDatabase("daniel", "daniel");


        Customer customer =new Customer("Daniel","Bojic","9792759","Dannekun@hotmail.com");

        data.addCustomer(customer);

        data.findAllCustomer();
        List<Customer> shunos = data.getShunos();
        String temp = "Hello ";
        for (int i = 0; i < shunos.size(); i++) {
            temp += shunos.get(i).firstName + " ";
        }


        return temp;
    }

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
        Customer customer = new Customer();
        model.addAttribute("customer", customer);

        return "regiser_form";
    }

   @PostMapping("/register")
   public String submitForm(@ModelAttribute("customer") Customer customer){
       System.out.println(customer);
       return "register_success";
   }


    @GetMapping("/index")
    public ModelAndView listCustomers() throws SQLException {
        System.out.println("det funkar");

        ModelAndView modelAndView = new ModelAndView();

        data.connectToAndQueryDatabase("daniel", "daniel");
        data.findAllCustomer();
       List<Customer> listCustomers = data.getShunos();

       modelAndView.addObject("listCustomers", listCustomers);



        return modelAndView;
    }


    @GetMapping("admin/addKund")
    public ModelAndView addCustomer() throws SQLException {
        System.out.println("det funkar");

        ModelAndView modelAndView = new ModelAndView();

        data.connectToAndQueryDatabase("daniel", "daniel");
        data.findAllCustomer();
        List<Customer> listCustomers = data.getShunos();

        modelAndView.addObject("listCustomers", listCustomers);




        return modelAndView;
    }



}