package com.example.demo1.controllers;

import com.example.demo1.models.Customer;
import com.example.demo1.repositories.CustomerRepository;
import com.example.demo1.twilioSendGrid.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * Not in use atm
 */

@RestController
@RequestMapping(path = "/customer")
public class Controller {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private MailService mailService;

    @GetMapping(path = "/addCustomer")
    public @ResponseBody
    String addCustomer(@RequestParam String firstname) throws IOException {

        Customer customer = new Customer();
        customer.setFirstName(firstname);

        customerRepository.save(customer);

        mailService.sendGreeting(customer);

        return "saved";
    }

    @GetMapping(path = "/showCustomers")
    public List<Customer> showCustomer() {

        return (List<Customer>) customerRepository.findAll();
    }
}
