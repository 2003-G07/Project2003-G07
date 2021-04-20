package com.example.demo1.models;

import javax.persistence.*;

/**
 * Created by Salah Abdinoor
 * 4/20/2021
 * 3:46 PM
 * Project2003-G073
 * Copyright: MIT
 */
@Entity
public class Child {

    @Id
    @GeneratedValue
    private Long id;

    private String namn;

    @JoinColumn(name = "countryId", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.ALL)
    private Country country;

    public Child(String namn, Country country) {
        this.namn = namn;
        this.country = country;
    }

    public Child(){

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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
