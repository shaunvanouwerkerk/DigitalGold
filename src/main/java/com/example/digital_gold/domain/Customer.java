package com.example.digital_gold.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Objects;

public class Customer extends Account {
    private final Logger logger = LoggerFactory.getLogger(Customer.class);

    private FullName fullName;
    private Address address;
    private CustomerDetails customerDetails;
    private final static String DEFAULT_SALT = null;

    public Customer(String username, String password, String salt, FullName fullName, Address address, CustomerDetails customerDetails) {
        super(username, password, salt);
        this.fullName = fullName;
        this.address = address;
        this.customerDetails = customerDetails;
    }

    public Customer(String username, String password,FullName fullName, Address address, CustomerDetails customerDetails) {
        super(username, password, DEFAULT_SALT);
        this.fullName = fullName;
        this.address = address;
        this.customerDetails = customerDetails;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        if (!super.equals(o)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(getFullName(), customer.getFullName()) && Objects.equals(getAddress(), customer.getAddress()) && Objects.equals(getCustomerDetails(), customer.getCustomerDetails());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getFullName(), getAddress(), getCustomerDetails());
    }

    @Override
    public String toString() {
        return "Customer{" +
                "fullName=" + fullName +
                ", address=" + address +
                ", customerDetails=" + customerDetails +
                '}';
    }
}

