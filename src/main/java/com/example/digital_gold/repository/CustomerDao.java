package com.example.digital_gold.repository;

import com.example.digital_gold.domain.Customer;

public interface CustomerDao {

    Customer save(Customer customer);
    boolean findCustomerByUsernameAndEmail (String username, String emailAddress);
    String findCustomerSalt (String username);
    String findCustomerHashPassword (String username);
    Customer findAndReturnCustomerByUsername (String username);

}
