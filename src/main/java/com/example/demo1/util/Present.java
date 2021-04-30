package com.example.demo1.util;

/**
 * Created by Salah Abdinoor
 * 4/28/2021
 * 2:04 PM
 * Heroku
 * Copyright: MIT
 */

import com.example.demo1.models.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * This class for the Order API to present the JSON file to Hakim in proper format..
 *
 */
public class Present {

    public Long id;
    public String name;
    public int price;
    public String category;

    public Present(Long id, String name, int price, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public Present(){

    }

    public Iterable<Present> format(List<Product> productList){

        List<Present> presentList = new ArrayList<>();

        for (int i = 0; i < productList.size(); i++) {
            presentList.add(new Present(productList.get(i).getId(), productList.get(i).getName(),
                    productList.get(i).getPrice(), productList.get(i).getCategory()));
        }

        return presentList;

    }
}
