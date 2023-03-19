package com.msbills.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter

public class BillAndUser {

    private Date billingDate;

    private String productBill;

    private Double totalPrice;

    private String username;
    private String firstName;
    private String lastName;
    private String email;

    public BillAndUser(Date billingDate, String productBill, Double totalPrice, String username, String firstName, String lastName, String email) {
        this.billingDate = billingDate;
        this.productBill = productBill;
        this.totalPrice = totalPrice;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
