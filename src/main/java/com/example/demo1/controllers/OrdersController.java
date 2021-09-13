package com.example.demo1.controllers;

import com.example.demo1.ApplicationConfiguration;
import com.example.demo1.models.*;
import com.example.demo1.repositories.*;
import com.example.demo1.twilioSendGrid.MailService;
import com.example.demo1.util.Present;
import com.example.demo1.util.Serialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 *
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
    @Autowired
    ApplicationConfiguration applicationConfiguration;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    MailService mailService = new MailService();



    /**
     * This method creates a new order based on some inputs
     * if address and customer don't exist in the system then it creates new once.
     */




    @PostMapping(path = "/add")
    public @ResponseBody
    String addOrder(@RequestParam List<Product> productList,
                    @RequestParam String fName,
                    @RequestParam String lName,
                    @RequestParam String tel,
                    @RequestParam String email,
                    @RequestParam String city,
                    @RequestParam String street,
                    @RequestParam String zip) throws IOException {

        Customer customer;
        Address address;

        // Create the time for the order.
        LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String formatDateTime = now.format(formatter);

        if (customerRepository == null) {
            customer = new Customer(fName, lName, tel, email);
            customerRepository.save(customer);
        }
        // First check to see if customer exists. If they don't create a new and save to repo, if they do use the same one.
        else if (customerRepository.findByFirstNameAndLastNameAndTelAndEmail(fName, lName, tel, email).isEmpty()) {
            customer = new Customer(fName, lName, tel, email);
            customerRepository.save(customer);
            mailService.sendGreeting(customer);

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



        // Create Order
        Orders orders = new Orders((long) totalPrice(productList), 1, customer, address);
        ordersRepository.save(orders);

        // add Order to OrderDetails
        System.out.println("ordersRepository.findAll() = " + ordersRepository.findAll());
        System.out.println("orders.getId() = " + orders.getId());
        OrderDetails orderDetails;
        for (Product product : productList) {
            orderDetails = new OrderDetails(orders, product);
            orderDetailsRepository.save(orderDetails);
            //System.out.println("orders = " + orders);

        }


        mailService.sendOrderConfirmation(orders.getId());

        return "Order Saved";

    }

    /**
     * Takes in a product list and their prices and returns the total price
     * for products in list.
     */

    public int totalPrice(List<Product> productList) {

        var totalPrice = 0;

        for (int i = 0; i < productList.size(); i++) {
            totalPrice += productList.get(i).getPrice();

        }

        return totalPrice;
    }

    /**
     * Takes in order id and returns the order information from id
     */

    @GetMapping(path = "/OrderById")
    public @ResponseBody
    Object[] PresentById(Long orderId) {

        Present present = new Present();
        var orders = ordersRepository.findById(orderId).get();
        var orderDetails = orderDetailsRepository.findByOrders(orders);
        List<Product> productList = new ArrayList<>();

        for (int i = 0; i < orderDetails.size(); i++) {

            var product = orderDetails.get(i).getProduct();
            productList.add(product);
        }

        Object[] ret = new Object[4];
        ret[0] = "Orderinfo: ";
        ret[1] = ordersRepository.findById(orderId);
        ret[2] = "Produkter: ";
        ret[3] = present.format(productList);

        return ret;
    }

    /**
     * Takes in the order id to patches(changes) the status from "new" to
     * "completed", status 1(one) is for new order and status 2(two)
     * is for completed order
     */

    @PatchMapping(path = "/changeStatus")
    public @ResponseBody
    String changeStatus(long orderId, int newStatus) {

        if (!ordersRepository.existsById(orderId)) {
            return "Ingen order finns med detta OrderID";
        }

        Orders orderToUpdate = ordersRepository.findById(orderId).get();

        if (orderToUpdate.getStatus() == newStatus) {
            return "Ordern har redan denna status";
        }

        if (newStatus == 0 || newStatus == 1 || newStatus == 2) {
            orderToUpdate.setStatus(newStatus);
            ordersRepository.save(orderToUpdate);
            return "Status ändrad på order: " + orderId + ", status: " + newStatus;
        } else {
            return "Felaktig status";
        }

    }

    /**
     * Method shows all order details (order, product and customer)
     * information
     */

    @GetMapping(path = "/showAll")
    public @ResponseBody
    Iterable<OrderDetails> showAll() {

        return orderDetailsRepository.findAll();
    }

    @GetMapping(path = "/showOrder")
    public @ResponseBody
    Iterable<Orders> showOrder() {

        return ordersRepository.findAll();
    }

    /**
     * Method is showing all order details for a specific status
     * where status is new = 1
     */

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

    /**
     * Method is showing all order details for a specific status
     * where status is completed = 2
     */

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
