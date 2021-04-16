package com.example.demo1.repositories;

import com.example.demo1.models.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Daniel Bojic
 * Date: 2021-04-15
 * Time: 14:46
 * Project: gr7java
 * Copyright: MIT
 */
public interface ProductRepository extends CrudRepository <Product, Long> {


    List<Product> findByName(String name);
    Product getProductById(long id);

    List<Product> findByImage(String image);



}
