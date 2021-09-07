package com.example.demo1.controllers;

import com.example.demo1.models.Product;
import com.example.demo1.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/product/")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Adds product to database, ID is auto-incremented
     * checks to see that there is not already an existing version of the same product
     *
     * @return String
     */

    @PostMapping(path = "/add")
    public @ResponseBody
    String addProduct(
            @RequestParam String name,
            @RequestParam(required = false) Integer storage,
            @RequestParam(required = false) String image,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Integer price,
            @RequestParam String category,
            @RequestParam boolean isVisible) {

        if (image == null)
            image = "https://www.translationvalley.com/wp-content/uploads/2020/03/no-iamge-placeholder.jpg";
        if (storage < 0) return "Storage can't be negative";
        if (price < 0) return "Price can't be negative";

        Product product = new Product(name, storage, image, description, price, category, isVisible);

        if ((productRepository.findByName(name).size() != 0)) {

            return "product already exists, use patch method instead";
        } else {
            productRepository.save(product);
        }

        return "Product Saved";
    }

    /**
     * Deletes product by using unique id for the product
     */

    @DeleteMapping(path = "/delete")
    public @ResponseBody
    String deleteProduct(@RequestParam Long id) {
        if ((productRepository.findById(id).isEmpty())) {
            return "Product doesn't exist";
        } else {
            productRepository.delete(productRepository.findById(id).get());

            return "Product deleted";
        }

    }

    /**
     * Shows all products
     * localhost:1234/showProducts
     *
     * @param
     * @return
     */

    //localhost:1234/product/showProducts
    @GetMapping(path = "/showAll")
    public @ResponseBody
    Iterable<Product> showProducts() {

        return productRepository.findAll();

    }

    /**
     * Shows product from its unique id
     */
    @GetMapping(path = "/showProductById")
    public @ResponseBody
    Optional<Product> showProductById(Long id) {
        return productRepository.findById(id);
    }

    /**
     * Method is used to change specific attributes for a product
     * ex. if price is changed no need to add new product instead
     * update only price
     */

    @PatchMapping(path = "/update")
    public @ResponseBody
    String updateProduct(
            @RequestParam Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer storage,
            @RequestParam(required = false) String image,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Integer price,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Boolean isVisible) {

        if (productRepository.findById(id).isEmpty()) {
            return "There is no product with that ID";
        } else {
            if (price != null && price < 0) return "price must be positive";
            if (storage != null && storage < 0) return "storage must be positive";

            var temp = productRepository.findById(id).get();

            if (name != null) temp.setName(name);
            if (storage != null) temp.setStorage(storage);
            if (image != null) temp.setImage(image);
            if (description != null) temp.setDescription(description);
            if (price != null) temp.setPrice(price);
            if (category != null) temp.setCategory(category);
            if (isVisible != null) temp.setVisible(isVisible);

            productRepository.save(temp);

            return "Product updated";
        }
    }
}