package com.example.demo1.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Salah Abdinoor
 * 4/20/2021
 * 1:30 PM
 * Project2003-G073
 * Copyright: MIT
 */
@Entity
public class Capital {

    @Id
    @GeneratedValue
    private Long id;

    private String namn;

    public Capital(String namn) {
        this.namn = namn;
    }
    public Capital() {

    }

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
}
