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
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseEntity<?> aanmeldenCustomer(@RequestBody Customer customer) {
        logger.info("New: " + customer);
        //registerService.register(customer);
        return ResponseEntity.created(URI.create("/register")).body("Successfull registration");
    }
    @PutMapping("/registreer")
    public ResponseEntity<?> aanmeldenKlant(@RequestParam String username) {
        Customer customer = new Customer(username,"wachtwoord", "leeg",new FullName("Jan","","Jansen"),
                new Address(1,"Straat","1111bb","city"),
                new CustomerDetails(Date.valueOf("2001-01-01"),"1232145","test@test.nl"));
        logger.info("New: " + customer);
        registerService.register(customer);
        return ResponseEntity.created(URI.create("/register")).body("Successfull registration");
    }

}
