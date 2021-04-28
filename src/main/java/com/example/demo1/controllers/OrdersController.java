package com.example.demo1.controllers;

import com.example.demo1.models.*;
import com.example.demo1.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

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
;


    @PostMapping(path = "/add")
    public @ResponseBody
    String addOrder(String productName, String customerName, String address) {

        if (productRepository.findByName(productName).isEmpty()) {
            return "There is no product named: " + productName;
        } else {

            Product product = productRepository.findByName(productName).get(0);

            LocalDateTime now = LocalDateTime.now();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            String formatDateTime = now.format(formatter);
            Address newAdress = new Address();
            OrderDetails orderDetails = new OrderDetails();

            if (addressRepository.findByAddress(address).isEmpty()) {

                newAdress.setAddress(address);

                addressRepository.save(newAdress);
            } else {

                newAdress = addressRepository.findByAddress(address).get();

            }

            if (customerRepository.findByFirstName(customerName).isEmpty()) {
                Customer customer = new Customer();

                customer.setFirstName(customerName);
                customerRepository.save(customer);

                Orders orders = new Orders(formatDateTime, (long) product.getPrice(), 1, customer, newAdress);

                ordersRepository.save(orders);

                orderDetails.setOrders(orders);
                orderDetails.setProduct(product);

                orderDetailsRepository.save(orderDetails);

                return "Order Saved";

            } else {

                var customer = customerRepository.findByFirstName(customerName).get(0);
                Orders orders = new Orders(formatDateTime, (long) product.getPrice(), 1, customer, newAdress);
                ordersRepository.save(orders);

                orderDetails.setOrders(orders);
                orderDetails.setProduct(product);

                orderDetailsRepository.save(orderDetails);
                return "Order Saved + new Customer";

            }

        }

    }


    @GetMapping(path = "/show")
    public @ResponseBody
    Iterable<OrderDetails> showAllOrder() {

        var orderDetails = orderDetailsRepository.findAll();

        var product = orderDetails.iterator().next().getProduct();
        //alltid en order
        var order = orderDetails.iterator().next().getOrders();

        orderDetails = Collections.singleton(new OrderDetails(order, product));


        return orderDetails;

    }

    @PatchMapping(path = "/changeStatus")
    public @ResponseBody
    String changeStatus() {

        var ordersIterable = ordersRepository.findAll();


        return "Status has changed";

    }
}
