package com.example.demo1.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Date;

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
    protected LocalDateTime date;
    protected int status;




    public Orders() {
    }

    public Orders(LocalDateTime date, int status) {
        this.date = date;
        this.status = status;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }



    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", date=" + date +
                ", status=" + status +
                '}';
    }
}
