package com.example.digital_gold.controller;

import com.example.digital_gold.domain.Customer;
import com.example.digital_gold.service.RegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    private RegisterService registerService;

    private final Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    public RegisterController(RegisterService registerService) {
        super();
        this.registerService = registerService;
        logger.info("New CustomerController");
    }

    @PutMapping("/register")
    //TODO DAVID AANPASSEN
    public Customer registerCustomer(@RequestParam String username) {
        Customer customer = registerService.register(customer);
        return customer;
    }

}
