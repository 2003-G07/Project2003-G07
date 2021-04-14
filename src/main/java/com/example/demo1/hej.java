package com.example.demo1;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Daniel Bojic
 * Date: 2021-04-13
 * Time: 15:45
 * Project: Project2003-G07
 * Copyright: MIT
 */
@RestController
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

        Customer customer = new Customer(firstNa, lastNa,teleNr,emailAd);
        data.connectToAndQueryDatabase("daniel", "daniel");
        data.findAllCustomer();

        List<Customer> shunos = data.getShunos();

        String y = "";

        for (int i = 0; i < shunos.size(); i++) {
            y += shunos.get(i).getFirstName();
        }


        return customer.getFirstName() + " " + customer.getLastName() + " " + customer.getTel() + " " + customer.getEmail()+ "\n" + "Din kamrater heter " + y;
    }


}