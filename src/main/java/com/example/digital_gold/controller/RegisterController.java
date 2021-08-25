package com.example.digital_gold.controller;

import com.example.digital_gold.domain.Address;
import com.example.digital_gold.domain.Customer;
import com.example.digital_gold.domain.CustomerDetails;
import com.example.digital_gold.domain.FullName;
import com.example.digital_gold.service.RegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.sql.Date;

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
    public ResponseEntity<?> registreerKlant(@RequestParam String username,
                                             @RequestParam String password,
                                             @RequestParam String firstName,
                                             @RequestParam String prefix,
                                             @RequestParam String lastName,
                                             @RequestParam int houseNumber,
                                             @RequestParam String streetName,
                                             @RequestParam String zipCode,
                                             @RequestParam String city,
                                             @RequestParam String dateOfBirth,
                                             @RequestParam String bsn,
                                             @RequestParam String emailAddress) {
        Customer customer = new Customer(username,password,new FullName(firstName,prefix,lastName),
                new Address(houseNumber,streetName,zipCode,city),
                new CustomerDetails(Date.valueOf(dateOfBirth),bsn,emailAddress));

        logger.info("New: " + customer);
        Customer registeredCustomer = registerService.register(customer);

        if(registeredCustomer != null) {
            return ResponseEntity.created(URI.create("/registration")).body("Successfull registration");
        } else {
            return ResponseEntity.internalServerError().body("Registration failed; username already exists");
        }
    }

}
