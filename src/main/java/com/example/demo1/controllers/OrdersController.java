package com.example.demo1.controllers;

import com.example.demo1.models.*;
import com.example.demo1.repositories.*;
import com.example.demo1.util.Present;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Created by Salah Abdinoor
 * 4/27/2021
 * 12:31 PM
 * Heroku
 * Copyright: MIT
 */
@RestController
@RequestMapping(path = "/orders/")
public class OrdersController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    /**
     * This method creates a new order based on some inputs
     * if address and customer don't exist in the system then it creates new once.
     *
     */

    @PostMapping(path = "/add")
    public @ResponseBody
    String addOrder(@RequestParam Long productId,
                    @RequestParam String fName,
                    @RequestParam String lName,
                    @RequestParam String tel,
                    @RequestParam String email,
                    @RequestParam String city,
                    @RequestParam String street,
                    @RequestParam String zip) {

        OrderDetails orderDetails;
        Customer customer;
        Address address;
        Product product;

        // Create the time for the order.
        LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String formatDateTime = now.format(formatter);


        // First check to see if customer exists. If they don't create a new and save to repo, if they do use the same one.
        if (customerRepository.findByFirstNameAndLastNameAndTelAndEmail(fName, lName, tel, email).isEmpty()) {
            customer = new Customer(fName, lName, tel, email);
            customerRepository.save(customer);

        } else {
            customer = customerRepository.findByFirstNameAndLastNameAndTelAndEmail(fName, lName, tel, email).get(0);
        }

        // Second check to see if address exists. If it doesn't create a new and save to repo, if it does use the same.
        if (addressRepository.findByCityAndAddressAndZip(city, street, zip).isEmpty()) {
            address = new Address(city, street, zip);
            addressRepository.save(address);

        } else {
            address = addressRepository.findByCityAndAddressAndZip(city, street, zip).get(0);
        }

        // third check to see if product exists. if it doesnt send error, if it does proceed.
        if (productRepository.findById(productId).isEmpty()) {
            return "There is no product named: " + productId;
        } else {

            product = productRepository.findById(productId).get();

            // Create Order
            Orders orders = new Orders(formatDateTime, (long) product.getPrice(), 1, customer, address);
            ordersRepository.save(orders);

            // add Order to OrderDetails
            orderDetails = new OrderDetails(orders, product);

            orderDetailsRepository.save(orderDetails);

            return "Order Saved";

        }

    }


    @GetMapping(path = "/OrderById")
    public @ResponseBody
    Iterable<Present> PresentById(Long orderDetailsId) {

        Present present = new Present();

        var orderDetails = orderDetailsRepository.findById(orderDetailsId).get();

        var orders = orderDetails.getOrders();
        var product= orderDetails.getProduct();


       return present.format(product,orders);

    }



    @GetMapping(path = "/show")
    public @ResponseBody
    Set<Present> showOneOrderById() {

        var orderDetails = orderDetailsRepository.findAll();

        var sizeOfProductList =ordersRepository.count();

        List<Product> productList = null;

        for (int j = 0; j < sizeOfProductList; j++) {

            var product = orderDetails.iterator().next().getProduct();

            productList.add(product);

        }

        //alltid en order
        var order = orderDetails.iterator().next().getOrders();

        Set<Present> present = null;

        for (int i = 0; i < sizeOfProductList; i++) {

            present = Collections.singleton(new Present(order, productList.get(i).getId(), productList.get(i).getName(), productList.get(i).getPrice(), productList.get(i).getQuant(), productList.get(i).getCategory()));

        }

        return present;

    }

    @PatchMapping(path = "/changeStatus")
    public @ResponseBody
    String changeStatus() {

        var ordersIterable = ordersRepository.findAll();


        return "Status has changed";

    }

    @GetMapping(path = "/showAll")
    public @ResponseBody
    Iterable<OrderDetails> showAll() {

        return orderDetailsRepository.findAll();

    }
}
