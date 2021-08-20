package com.example.digital_gold.repository;

import com.example.digital_gold.domain.Customer;

public interface CustomerDao {

    Customer save(Customer customer);
    Customer findByUsername(String username);
}
