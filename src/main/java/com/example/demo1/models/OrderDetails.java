package com.example.demo1.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    protected Long ordersId;
    protected Long productId;

    public OrderDetails() {
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "id=" + id +
                ", ordersId=" + ordersId +
                ", productId=" + productId +
                '}';
    }
}
