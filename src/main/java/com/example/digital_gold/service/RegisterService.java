package com.example.digital_gold.service;

import com.example.digital_gold.domain.Customer;
import com.example.digital_gold.helper.SaltMaker;
import com.example.digital_gold.repository.JdbcCustomerDao;
import com.example.digital_gold.repository.RootRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;


@Service
public class RegisterService {

    private RootRepository rootRepository;
    private SaltMaker saltMaker;
    private HashService hashService;

    private final Logger logger = LoggerFactory.getLogger(RegisterService.class);

    @Autowired
    public RegisterService(RootRepository rootRepository, SaltMaker saltMaker, HashService hashService) {
        this.rootRepository = rootRepository;
        this.saltMaker = saltMaker;
        this.hashService = hashService;
        logger.info("New RegisterService");
    }

    public Customer register(Customer customer) {
        String salt = saltMaker.generateSalt();
        customer.setSalt(salt);
        String hashPassword = customer.getPassword() + salt;
        customer.setPassword(hashService.hash(hashPassword));
        if (rootRepository.findCustomerByUsername(customer.getUsername())) {
            return null;
        } else {
            rootRepository.saveCustomer(customer);
            return customer;
        }
    }

    public RootRepository getRootRepository() {
        return rootRepository;
    }
}
