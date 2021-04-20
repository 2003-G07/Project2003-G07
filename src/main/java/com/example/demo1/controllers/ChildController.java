package com.example.demo1.controllers;

import com.example.demo1.models.Capital;
import com.example.demo1.models.Child;
import com.example.demo1.models.Country;
import com.example.demo1.repositories.CapitalRepository;
import com.example.demo1.repositories.ChildRepository;
import com.example.demo1.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Salah Abdinoor
 * 4/20/2021
 * 3:53 PM
 * Project2003-G073
 * Copyright: MIT
 */
@RestController
@RequestMapping("/child")
public class ChildController {

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    CapitalRepository capitalRepository;

    @Autowired
    ChildRepository childRepository;



    @GetMapping("/all")
    public Iterable<Child> getAllChildren(){

        return childRepository.findAll();
    }

    @GetMapping("/add")
    public String addChild(@RequestParam String barn, @RequestParam String land, @RequestParam String huvudStad){

        Country existingCountry = countryRepository.findByNamn(land);

        if (existingCountry == null){

            CountryController countryController = new CountryController();

            var country = countryController.addCountry(land, huvudStad);

            Child child = new Child(barn, country);

            childRepository.save(child);

            return "New Child Added:" +child + "!  And new country" + country;
        } else {
            Child child = new Child(barn, existingCountry);
            childRepository.save(child);
            return "New Child Added: " + child;


        }





    }
}
