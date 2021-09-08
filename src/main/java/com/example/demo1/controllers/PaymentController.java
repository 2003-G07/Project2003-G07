package com.example.demo1.controllers;

import com.example.demo1.ApplicationConfiguration;
import com.example.demo1.models.OrderDetails;
import com.example.demo1.models.Payment;
import com.example.demo1.repositories.OrderDetailsRepository;
import com.example.demo1.util.PaymentDto;
import com.example.demo1.util.Serialize;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * Created by Daniel Bojic
 * Date: 2021-09-07
 * Time: 17:11
 * Project: gr7java
 * Copyright: MIT
 */
@Controller
@RequestMapping(path = "/payment")
public class PaymentController {

    @Autowired
    ApplicationConfiguration applicationConfiguration;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    OrderDetailsRepository orderDetailsRepository;


    @PostMapping(path = "/testing")
    public @ResponseBody
    String testing() throws IOException {


        Serialize serialize = new Serialize();

        System.out.println("HOST!!!!!: "+applicationConfiguration.getHost());


        serialize.sendDTO(applicationConfiguration.getHost());


        return "Det funkade!";
    }

    @PostMapping(path = "/receive")
    public @ResponseBody String receive(@RequestBody PaymentDto paymentDto){

        for (OrderDetails order: orderDetailsRepository.findAll()) {
                    if(order.getOrders().getId() == Long.parseLong(paymentDto.getReference())){
                        System.out.println("STATUS: PAID!!!");
                        System.out.println(order.getOrders());
                    }
        }

        System.out.println("STATUS UPPDATERAD!!");

        return "bra jobbat!";

    }


}
