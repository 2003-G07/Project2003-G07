package com.example.demo1.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    protected String date;
    protected Long cost;
    protected int status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerId", referencedColumnName = "id")
    protected Customer customer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addressId", referencedColumnName = "id")
    protected Address address;

    public Orders(String date, Long cost, int status, Customer customer, Address address) {
        this.date = date;
        this.cost = cost;
        this.status = status;
        this.customer = customer;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", date=" + date +
                ", cost=" + cost +
                ", status=" + status +
                ", customer=" + customer +
                ", address=" + address +
                '}';
    }

    public Orders() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}