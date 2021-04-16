package com.example.demo1.controllers;

import com.example.demo1.models.Product;
import com.example.demo1.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Salah Abdinoor
 * 4/16/2021
 * 4:27 PM
 * Project2003-G073
 * Copyright: MIT
 */

@RestController
@RequestMapping(path = "/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;


    //localhost:1234/product/addProduct?productName=XXXXX
    @GetMapping(path = "/addProduct")
    public @ResponseBody String addProduct(@RequestParam String productName){

        Product product = new Product();
        product.setName(productName);

        productRepository.save(product);

        return "Product Saved";

    }

    // Ändra så att produkten bara gömmer sid! Test ATM
    //localhost:1234/product/deleteProduct?productId=1234
    @GetMapping(path = "/deleteProduct")
    public @ResponseBody String deleteProduct(@RequestParam Long productId){

        productRepository.delete(productRepository.getProductById(productId));
        return "Product deleted";

    }
    //localhost:1234/product/showProducts
    @GetMapping(path = "/showProducts")
    public @ResponseBody Iterable<Product> showProducts(){

        return productRepository.findAll();

    }


}