package com.example.demo1.util;

import java.io.Serializable;

public class PaymentDto implements Serializable {
    private String reference;
    private long amount;

    public PaymentDto() {
    }

    public PaymentDto(String reference, long amount) {
        this.reference = reference;
        this.amount = amount;
    }

    public String getReference() {
        return reference;
    }

    public long getAmount() {
        return amount;
    }
}
