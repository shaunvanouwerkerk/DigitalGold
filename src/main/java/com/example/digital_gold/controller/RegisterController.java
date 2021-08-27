package com.example.digital_gold.controller;

/**
 * @author David Truijens
 * Methode register Customer is endpoint voor registratie van een klant.
 * Benodigd: NoArgs constructors in Account en Customer
 * HTTP request: PUT request met JSON body
 * JSON format en voorbeeld : resources/JSON HTTP PUT.txt
 */


import com.example.digital_gold.domain.Customer;
import com.example.digital_gold.service.RegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;


@RestController
public class RegisterController {

    private RegisterService registerService;

    private final Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
        logger.info("New RegisterController");
    }

    @PutMapping("/register")
    public ResponseEntity<?> registerCustomer(@RequestBody Customer customer) {
        logger.info("Uit body via JSON aangemaakt: " + customer);
        Customer registeredCustomer = registerService.register(customer);

        if(registeredCustomer != null) {
            return ResponseEntity.created(URI.create("/register")).build();
        } else {
            return ResponseEntity.badRequest().body("Registration failed; username already exists");
        }
    }

}
