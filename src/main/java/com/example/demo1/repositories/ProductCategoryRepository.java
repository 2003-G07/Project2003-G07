package com.example.demo1.repositories;

import com.example.demo1.models.Address;
import com.example.demo1.models.ProductCategory;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by Henrik Elofsson
 * Date: 2021-04-16
 * Time: 14:53
 * Project: Project2003-G07
 * Copyright: MIT
 */
public interface ProductCategoryRepository extends CrudRepository<ProductCategory, Long> {

    Optional<ProductCategory> findById(Long id);

}
