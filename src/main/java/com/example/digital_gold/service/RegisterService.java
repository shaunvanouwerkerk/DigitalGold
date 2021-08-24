package com.example.digital_gold.service;

import com.example.digital_gold.domain.Customer;
import com.example.digital_gold.repository.RootRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

//TODO aanpassen vanalles Shaun
// salt aanmaken : String salt = saltMaker.generateSalt();
// hashService.hash()
//oftewel registermethode aanpassen


@Service
public class RegisterService {

    private RootRepository rootRepository;

    private final Logger logger = LoggerFactory.getLogger(RegisterService.class);

    @Autowired
    public RegisterService(RootRepository rootRepository) {
        this.rootRepository = rootRepository;
        logger.info("New CustomerService");
    }

    public Customer register(Customer customer) {

        rootRepository.saveCustomer(customer);
        return customer;
    }



    public RootRepository getRootRepository() {
        return rootRepository;
    }
}
