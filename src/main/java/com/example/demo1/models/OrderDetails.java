package com.example.demo1.models;

import javax.persistence.*;

@Entity
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ordersId", referencedColumnName = "id")
    protected Orders orders;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "productId", referencedColumnName = "id")
    protected Product product;

    public OrderDetails(Orders orders, Product product) {
        this.orders = orders;
        this.product = product;
    }

    public OrderDetails(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "id=" + id +
                ", orders=" + orders +
                ", product=" + product +
                '}';
    }
}
