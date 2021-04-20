package com.example.demo1.controllers;

import com.example.demo1.models.Capital;
import com.example.demo1.models.Country;
import com.example.demo1.repositories.CapitalRepository;
import com.example.demo1.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Salah Abdinoor
 * 4/20/2021
 * 2:42 PM
 * Project2003-G073
 * Copyright: MIT
 */
@RestController
@RequestMapping("/country")
public class CountryController {

    @Autowired
    CapitalRepository capitalRepository;
    @Autowired
    CountryRepository countryRepository;



    @GetMapping("/add")
    public Country addCountry(@RequestParam String land, @RequestParam String huvudStad){

        Capital existingCapital = capitalRepository.findByNamn(huvudStad);

        if (existingCapital == null){

            Capital newCapital =  new Capital(huvudStad);

            Country country = new Country(land, newCapital);

            countryRepository.save(country);

            return country;

            git checkout Hibernate-Lektion-Test
            git branch --set-upstream-to=origin/Hibernate-Lektion-Test
        } else {

            Country country = new Country(land, existingCapital);

            countryRepository.save(country);

            return country;

        }




    }

    @GetMapping("/all")
    public Iterable<Country> getAllCountries(){

        return countryRepository.findAll();
    }
}

