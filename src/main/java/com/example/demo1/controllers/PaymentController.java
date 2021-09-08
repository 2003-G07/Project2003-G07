package com.example.demo1.controllers;

import com.example.demo1.ApplicationConfiguration;
import com.example.demo1.util.Serialize;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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


    @PostMapping(path = "/testing")
    public @ResponseBody
    String testing () throws IOException {


        Serialize serialize = new Serialize();

        System.out.println("HOST!!!!!: "+applicationConfiguration.getHost());


        serialize.sendDTO(applicationConfiguration.getHost());






        return "Det funkade!";
    }

}
