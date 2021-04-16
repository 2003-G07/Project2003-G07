package com.example.demo1.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Henrik Elofsson
 * Date: 2021-04-16
 * Time: 14:48
 * Project: Project2003-G07
 * Copyright: MIT
 */
@Entity
public class ProductCategory{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    protected Long categoryId;
    protected Long productId;

    public ProductCategory() {
    }

    public Long getId() {
        return id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public Long getProductId() {
        return productId;
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", productId=" + productId +
                '}';
    }
}
