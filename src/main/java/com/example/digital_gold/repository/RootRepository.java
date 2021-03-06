package com.example.digital_gold.repository;

import com.example.digital_gold.domain.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RootRepository {

    private final Logger logger = LoggerFactory.getLogger(RootRepository.class);

    private CustomerDao customerDao;

    @Autowired
    public RootRepository(CustomerDao customerDao) {
        this.customerDao = customerDao;
        logger.info("New RootRepository");
    }


    public Customer saveCustomer(Customer customer) {
        return customerDao.save(customer);
    }


    public boolean findCustomerByUsername(String username) {
        return customerDao.findCustomerByUsername(username);
    }

}