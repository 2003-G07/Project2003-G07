package com.example.demo1.controllers;

import com.example.demo1.models.Product;
import com.example.demo1.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Created by Salah Abdinoor
 * 4/16/2021
 * 4:27 PM
 * Project2003-G073
 * Copyright: MIT
 */
@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Adds product to database, ID is auto-incremented
     * checks to see that there is not already an existing version of the same product
     * localhost:1234/addProduct
     * @return String
     */

    @PostMapping(path = "/addProduct")
    public @ResponseBody String addProduct(
                            @RequestParam String name,
                            @RequestParam (required = false) int storage,
                            @RequestParam (required = false) String image,
                            @RequestParam (required = false) String description,
                            @RequestParam (required = false) int price,
                            @RequestParam  String category,
                            @RequestParam  boolean isVisible ){

        System.out.println(name);

        Product product = new Product(name,storage, image, description, price,category,isVisible);

        if ((productRepository.findByName(name).size()!=0)){

            return "product already exists, use put method instead";
        }else {
            productRepository.save(product);
        }


        return "Product Saved";

    }

    // Ändra så att produkten bara gömmer sid! Test ATM
    //localhost:1234/product/deleteProduct?productId=1234
    @DeleteMapping(path = "/deleteProduct")
    public @ResponseBody String deleteProduct(@RequestParam Long id){



        if ((productRepository.findById(id).isEmpty())){
            return "Product doesn't exist";
        }else {

            productRepository.delete(productRepository.findById(id).get());
            return "Product deleted";
        }


        /**
         * Shows all products
         * localhost:1234/showProducts
         * @param
         * @return
         */

    }
    //localhost:1234/product/showProducts
    @GetMapping(path = "/showProducts")
    public @ResponseBody Iterable<Product> showProducts(){

        return productRepository.findAll();

    }

    /**
     * Shows product from id
     * localhost:1234/product/showProductById
     * @param id
     * @return
     */

    @GetMapping(path = "/showProductById")
    public @ResponseBody Optional<Product> showProductById(Long id){

        return productRepository.findById(id);

    }

    @PutMapping(path = "/updateProductById")
    public @ResponseBody String updateProduct(
                            @RequestParam Long id,
                            @RequestParam (required = false) String name,
                            @RequestParam (required = false) int storage,
                            @RequestParam (required = false) String image,
                            @RequestParam (required = false) String description,
                            @RequestParam (required = false) int price,
                            @RequestParam (required = false) String category,
                            @RequestParam (required = false) boolean isVisible ){

        var putId = productRepository.findById(id);

        if (productRepository.findById(id).isEmpty()){

            return "There is no product with that ID";
        }
        else {

           var temp =  productRepository.findById(id).get();

           temp.setName(name);
           temp.setStorage(storage);
           temp.setImage(image);
           temp.setDescription(description);
           temp.setPrice(price);
           temp.setCategory(category);
           temp.setVisible(isVisible);

           productRepository.save(temp);

           return "Product updated";

        }
    }
}