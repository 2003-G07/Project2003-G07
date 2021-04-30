package com.example.demo1.models;

import javax.persistence.*;

@Entity

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    String name;
    protected int storage;
    protected String image;
    protected String description;
    int price;
    protected String category;
    boolean isVisible;

    int quant;

    public Product() {
    }

    public Product(String name, int storage, String image, String description, int price, String category, boolean isVisible) {
        setName(name);
        setStorage(storage);
        setImage(image);
        setDescription(description);
        setPrice(price);
        setCategory(category);
        setVisible(isVisible);
    }

    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
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
