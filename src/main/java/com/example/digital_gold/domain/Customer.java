package com.example.digital_gold.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Objects;

public class Customer extends Account {
    private final Logger logger = LoggerFactory.getLogger(Customer.class);

    private Fullname fullname;
    private Address address;
    private CustomerDetails customerDetails;

    public Customer(String username, String password, String salt) {
        super(username, password, salt);
    }

    public Customer(String username, String password) {
        super(username, password);
    }

    public Fullname getFullname() {
        return fullname;
    }

    public void setFullname(Fullname fullname) {
        this.fullname = fullname;
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
        return Objects.equals(getFullname(), customer.getFullname()) && Objects.equals(getAddress(), customer.getAddress()) && Objects.equals(getCustomerDetails(), customer.getCustomerDetails());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getFullname(), getAddress(), getCustomerDetails());
    }

    @Override
    public String toString() {
        return "Customer{" +
                "fullname=" + fullname +
                ", address=" + address +
                ", customerDetails=" + customerDetails +
                '}';
    }
}

