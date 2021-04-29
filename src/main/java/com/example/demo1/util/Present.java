package com.example.demo1.util;

/**
 * Created by Salah Abdinoor
 * 4/28/2021
 * 2:04 PM
 * Heroku
 * Copyright: MIT
 */

import com.example.demo1.models.Orders;
import com.example.demo1.models.Product;
import org.apache.catalina.LifecycleState;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class for the Order API to present the JSON file to Hakim in proper format..
 *
 */
public class Present {

    public Orders orders;
    public Long id;
    public String name;
    public int price;
    public int quantity;
    public String category;

    public Present(Orders orders, Long id, String name, int price, int quantity, String category) {
        this.orders = orders;
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    public Present(){

    }


    public Iterable<Present> format(Product product, Orders orders){

        List<Present> presentList = new ArrayList<>();
        Present presents = new Present(orders, product.getId(),product.getName(), product.getPrice(), product.getQuant(), product.getCategory());

        presentList.add(presents);

        return presentList;

    }
}
