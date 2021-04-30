package com.example.demo1.controllers;

import com.example.demo1.models.*;
import com.example.demo1.repositories.*;
import com.example.demo1.util.Present;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
    String addOrder(@RequestParam List<Long> productId,
                    @RequestParam String fName,
                    @RequestParam String lName,
                    @RequestParam String tel,
                    @RequestParam String email,
                    @RequestParam String city,
                    @RequestParam String street,
                    @RequestParam String zip) {

        Customer customer;
        Address address;
        List<Product> productList = new ArrayList<>();

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
        if (productRepository.findById(productId.get(0)).isEmpty()) {
            return "There is no product named: " + productId;
        } else {

            for (int i = 0; i < productId.size(); i++) {

                productList.add(productRepository.findById(productId.get(i)).get());

            }



            // Create Order
            Orders orders = new Orders(formatDateTime, (long) totalPrice(productList), 1, customer, address);
            ordersRepository.save(orders);

            // add Order to OrderDetails
            OrderDetails orderDetails;
            for (Product product : productList) {
               orderDetails = new OrderDetails(orders, product);
                orderDetailsRepository.save(orderDetails);
                System.out.println("orders = " + orders);

            }

            return "Order Saved";

        }

    }

    public int totalPrice(List<Product> productList){

        var totalPrice = 0;

        for (int i = 0; i < productList.size() ; i++) {

            totalPrice += productList.get(i).getPrice();

        }

        return totalPrice;
    }


    @GetMapping(path = "/OrderById")
    public @ResponseBody
    Object[] PresentById(Long orderId) {

        Present present = new Present();
        var orders = ordersRepository.findById(orderId).get();
        var orderDetails = orderDetailsRepository.findByOrders(orders);
        List<Product> productList = new ArrayList<>();

        for (int i = 0; i < orderDetails.size(); i++) {

            var product= orderDetails.get(i).getProduct();
            productList.add(product);
        }

        Object[] ret = new Object[4];
        ret[0] = "Orderinfo: ";
        ret[1] = ordersRepository.findById(orderId);
        ret[2] = "Produkter: ";
        ret[3] = present.format(productList);

        return ret;
    }



    @GetMapping(path = "/show")
    public @ResponseBody
    Set<Present> showOneOrderById() {

        var orderDetails = orderDetailsRepository.findAll();

        var sizeOfProductList = ordersRepository.count();

        List<Product> productList = null;

        for (int j = 0; j < sizeOfProductList; j++) {

            var product = orderDetails.iterator().next().getProduct();

            productList.add(product);

        }

        //alltid en order
        var order = orderDetails.iterator().next().getOrders();

        Set<Present> present = null;

        for (int i = 0; i < sizeOfProductList; i++) {


        }

        return present;

    }

    @PatchMapping(path = "/changeStatus")
    public @ResponseBody
    String changeStatus(long orderId, int newStatus) {

        if (!ordersRepository.existsById(orderId)){
            return "Ingen order finns med detta OrderID";
        }

        Orders orderToUpdate = ordersRepository.findById(orderId).get();

        if (orderToUpdate.getStatus() == newStatus){
            return "Ordern har redan denna status";
        }

        if (newStatus == 0 || newStatus == 1 || newStatus == 2){
            orderToUpdate.setStatus(newStatus);
            ordersRepository.save(orderToUpdate);
            return "Status ändrad på order: " + orderId + ", status: " + newStatus;
        }else {
            return "Felaktig status";
        }



    }

    @GetMapping(path = "/showAll")
    public @ResponseBody
    Iterable<OrderDetails> showAll() {

        return orderDetailsRepository.findAll();

    }

    @GetMapping(path = "/showAllNew")
    public @ResponseBody
    List<Object> showAllActive() {

        List<Object> ret = new ArrayList<>();
        List<Orders> activeOrderList = ordersRepository.findByStatus(1);

        for (int i = 0; i < activeOrderList.size(); i++) {
            ret.add(PresentById(activeOrderList.get(i).getId()));
        }

        return ret;

    }



    @GetMapping(path = "/showAllComplete")
    public @ResponseBody
    List<Object> showAllComplete() {

        List<Object> ret = new ArrayList<>();
        List<Orders> activeOrderList = ordersRepository.findByStatus(2);

        for (int i = 0; i < activeOrderList.size(); i++) {
            ret.add(PresentById(activeOrderList.get(i).getId()));
        }

        return ret;

    }

}
