package com.example.demo1.models;

public class CartService {
    Product product;
    int quantity;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public CartService() {
    }

    public CartService(Product pro, int kvant) {
        setProduct(pro);
        setQuantity(kvant);
    }
}
