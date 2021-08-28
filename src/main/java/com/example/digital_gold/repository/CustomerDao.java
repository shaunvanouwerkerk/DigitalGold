package com.example.digital_gold.repository;

import com.example.digital_gold.domain.Customer;
//TODO FIONA EN SHAUN EN DAVID

public interface CustomerDao {

    Customer save(Customer customer);
    boolean findCustomerByUsernameAndEmail (String username, String emailAddress);

}
