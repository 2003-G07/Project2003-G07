package com.example.demo1.repositories;

import com.example.demo1.models.Capital;
import com.example.demo1.models.Child;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Salah Abdinoor
 * 4/20/2021
 * 3:51 PM
 * Project2003-G073
 * Copyright: MIT
 */
public interface ChildRepository extends CrudRepository<Child, Long> {

    Child findByNamn(String namn);

}
