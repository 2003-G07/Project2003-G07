package com.example.demo1.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Henrik Elofsson
 * Date: 2021-04-16
 * Time: 14:40
 * Project: Project2003-G07
 * Copyright: MIT
 */
@Entity
public class Category{


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    protected String namn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamn() {
        return namn;
    }

    public void setNamn(String namn) {
        this.namn = namn;
    }



    public Category() {
    }

    public Category(String namn) {
        this.namn = namn;
    }
}
