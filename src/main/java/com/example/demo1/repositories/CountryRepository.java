package com.example.demo1.repositories;

import com.example.demo1.models.Country;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Salah Abdinoor
 * 4/20/2021
 * 1:51 PM
 * Project2003-G073
 * Copyright: MIT
 */
public interface CountryRepository extends CrudRepository<Country, Long> {

    Country findByNamn(String namn);

}
