package com.example.demo1.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Daniel Bojic
 * Date: 2021-04-15
 * Time: 14:18
 * Project: gr7java
 * Copyright: MIT
 */
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    protected String name;
    protected int storage;
    protected String image;
    protected String description;
    protected double price;
    protected String category;

    public Product(){}


    public Product(String name, int storage, String image, String description, double price, String category){
        setName(name);
        setStorage(storage);
        setImage(image);
        setDescription(description);
        setPrice(price);
        setCategory(category);
    }


    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        System.out.println("hej");

        isVisible = visible;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", storage=" + storage +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                '}';
    }
}
