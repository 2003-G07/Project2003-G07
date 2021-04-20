package com.example.demo1.models;

import javax.persistence.*;

/**
 * Created by Salah Abdinoor
 * 4/20/2021
 * 1:29 PM
 * Project2003-G073
 * Copyright: MIT
 */
@Entity
public class Country {

    @Id
    @GeneratedValue
    private Long id;

    private String namn;

    @JoinColumn(name = "capitalId", referencedColumnName = "id")
    @OneToOne(cascade = CascadeType.ALL)
    private Capital capital;

    public Country(String namn, Capital capital) {
        this.namn = namn;
        this.capital = capital;
    }
    public Country() {
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

    public Capital getCapital() {
        return capital;
    }

    public void setCapital(Capital capital) {
        this.capital = capital;
    }
}
