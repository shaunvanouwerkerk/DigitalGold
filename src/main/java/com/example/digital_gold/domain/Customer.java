package com.example.digital_gold.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.util.Objects;

/*
@Author Jany Gaal
*/

public class Customer extends Account {
    private final Logger logger = LoggerFactory.getLogger(Customer.class);

    @Valid
    private FullName fullName;
    @Valid
    private Address address;
    @Valid
    private CustomerDetails customerDetails;
    private final static String DEFAULT_SALT = null;


    public Customer(String username, String password, String salt, boolean status, FullName fullName, Address address, CustomerDetails customerDetails) {
        super(username, password, salt,status);
        this.fullName = fullName;
        this.address = address;
        this.customerDetails = customerDetails;
    }

    public Customer(String username, String password, FullName fullName, Address address, CustomerDetails customerDetails) {
        super(username, password, DEFAULT_SALT,true);
        this.fullName = fullName;
        this.address = address;
        this.customerDetails = customerDetails;
    }

    public Customer() {
    }

    public FullName getFullName() {
        return fullName;
    }

    public void setFullName(FullName fullName) {
        this.fullName = fullName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public CustomerDetails getCustomerDetails() {
        return customerDetails;
    }

    public void setCustomerDetails(CustomerDetails customerDetails) {
        this.customerDetails = customerDetails;
    }


    @Override
    public String toString() {
        return "Customer{" +
                "fullName=" + fullName +
                ", address=" + address +
                ", customerDetails=" + customerDetails +
                "} " + super.toString();
    }
}

