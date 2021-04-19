package com.example.demo1.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Henrik Elofsson
 * Date: 2021-04-16
 * Time: 14:17
 * Project: Project2003-G07
 * Copyright: MIT
 */
@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    protected Long customerId;
    protected String date;
    protected String image;
    protected Long cost;
    protected int status;
    protected Long addressId;


    public Orders() {
    }

    public Orders(String image, Long cost, int status) {
        this.date = currentTime();
        this.image = image;
        this.cost = cost;
        this.status = status;
    }

    public String currentTime(){

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();


        return dtf.format(now);
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getDate() {
        return date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }


    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", date=" + date +
                ", image='" + image + '\'' +
                ", cost=" + cost +
                ", status=" + status +
                ", addressId=" + addressId +
                '}';
    }
}
