package com.example.demo1.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel Bojic
 * Date: 2021-04-19
 * Time: 17:54
 * Project: gr7java
 * Copyright: MIT
 */
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

    public CartService(){

    }

    public CartService(Product pro, int kvant){
        setProduct(pro);
        setQuantity(kvant);
    }


}
