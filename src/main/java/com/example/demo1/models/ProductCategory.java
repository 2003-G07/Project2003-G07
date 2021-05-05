package com.example.demo1.models;

import javax.persistence.*;

@Entity
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoryId", referencedColumnName = "id")
    protected Category category;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "productId", referencedColumnName = "id")
    protected Product product;

    public ProductCategory(Category category, Product product) {
        this.category = category;
        this.product = product;
    }

    public ProductCategory() {

    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "id=" + id +
                ", category=" + category +
                ", product=" + product +
                '}';
    }
}
