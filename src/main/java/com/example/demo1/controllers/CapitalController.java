package com.example.demo1.controllers;

import com.example.demo1.models.Capital;
import com.example.demo1.repositories.CapitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Salah Abdinoor
 * 4/20/2021
 * 1:54 PM
 * Project2003-G073
 * Copyright: MIT
 */
@RestController
@RequestMapping("/capital")
public class CapitalController {

    @Autowired
    CapitalRepository capitalRepository;

    @GetMapping("/all")
    public Iterable<Capital> getAllCapitals(){

        return capitalRepository.findAll();
    }

    @GetMapping("/add")
    public String addCapital(@RequestParam String namn){
        Capital capital = new Capital();

        capital.setNamn(namn);

        capitalRepository.save(capital);

        return namn + "Added";

    }
}
