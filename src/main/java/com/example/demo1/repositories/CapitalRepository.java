package com.example.demo1.repositories;

import com.example.demo1.models.Capital;
import org.springframework.data.repository.CrudRepository;
import org.w3c.dom.stylesheets.LinkStyle;

/**
 * Created by Salah Abdinoor
 * 4/20/2021
 * 1:53 PM
 * Project2003-G073
 * Copyright: MIT
 */
public interface CapitalRepository extends CrudRepository<Capital, Long> {

    Capital findByNamn(String namn);

}
