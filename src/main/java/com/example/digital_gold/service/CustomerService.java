package com.example.digital_gold.service;

import com.example.digital_gold.domain.Customer;
import com.example.digital_gold.repository.RootRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;


@Service
public class CustomerService {

    private RootRepository rootRepository;

    private final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    public CustomerService(RootRepository rootRepository) {
        this.rootRepository = rootRepository;
        logger.info("New CustomerService");
    }

    public Customer register(String username) {
        String password = generatePassword();
        Customer customer = new Customer("bla", password, "bla", "bla", "bla", Date.valueOf("2021-08-20"),
                1548646546, 10, "bla","bla", "bla");
        rootRepository.saveCustomer(customer);
        return customer;
    }

    private String generatePassword() {
        return "welkom123";
    }

    public RootRepository getRootRepository() {
        return rootRepository;
    }
}
