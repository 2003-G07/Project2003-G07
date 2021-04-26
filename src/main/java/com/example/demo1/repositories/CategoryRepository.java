package com.example.demo1.repositories;

import com.example.demo1.models.Category;
import com.example.demo1.models.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Henrik Elofsson
 * Date: 2021-04-16
 * Time: 14:46
 * Project: Project2003-G07
 * Copyright: MIT
 */
public interface CategoryRepository extends CrudRepository<Category, Long> {

    List<Category> findByNamn(String namn);

}
