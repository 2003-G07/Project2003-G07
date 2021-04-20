package com.example.demo1.repositories;

import com.example.demo1.models.Customer;
import com.example.demo1.models.Produkt;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Daniel Bojic
 * Date: 2021-04-15
 * Time: 14:46
 * Project: gr7java
 * Copyright: MIT
 */
public interface ProduktRepository extends CrudRepository <Produkt, Long> {


    List<Produkt> findByName(String name);
    Produkt getProduktById(long id);

}
