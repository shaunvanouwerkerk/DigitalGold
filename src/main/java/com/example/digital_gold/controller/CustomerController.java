package com.example.digital_gold.controller;

import com.example.digital_gold.domain.Customer;
import com.example.digital_gold.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    private CustomerService customerService;

    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    public CustomerController(CustomerService customerService) {
        super();
        this.customerService = customerService;
        logger.info("New CustomerController");
    }

    @PutMapping("/register")
    public Customer registerCustomer(@RequestParam String username) {
        Customer customer = customerService.register(username);
        return customer;
    }

}
