package com.example.demo1.util;

import com.example.demo1.models.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is to show specific information from the API in proper JSON format
 */
public class Present {

    public Long id;
    public String name;
    public int price;
    public String category;
    public int quant;

    public Present(Long id, String name, int price, String category, int quant) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.quant = quant;
    }

    public Present() {

    }

    public Iterable<Present> format(List<Product> productList) {

        List<Present> presentList = new ArrayList<>();

        for (int i = 0; i < productList.size(); i++) {
            presentList.add(new Present(productList.get(i).getId(), productList.get(i).getName(),
                    productList.get(i).getPrice(), productList.get(i).getCategory(), productList.get(i).getQuant()));
        }

        return presentList;

    }
}
