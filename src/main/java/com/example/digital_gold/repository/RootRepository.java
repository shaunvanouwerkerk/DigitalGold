package com.example.digital_gold.repository;

import com.example.digital_gold.domain.Administrator;
import com.example.digital_gold.domain.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RootRepository {

    private final Logger logger = LoggerFactory.getLogger(RootRepository.class);

    private CustomerDao customerDao;
    private AdministratorDao administratorDao;

    @Autowired
    public RootRepository(CustomerDao customerDao, AdministratorDao administratorDao) {
        this.customerDao = customerDao;
        this.administratorDao = administratorDao;
        logger.info("New RootRepository");
    }


    public Customer saveCustomer(Customer customer) {
        return customerDao.save(customer);
    }
    public Administrator saveAdministrator(Administrator administrator) {
        return administratorDao.save(administrator);
    }

    public boolean findCustomerByUsernameAndEmail(String username, String emailAddress) {
        return customerDao.findCustomerByUsernameAndEmail(username, emailAddress);
    }

    public boolean findAdministratorByUsername(String username) {
        return administratorDao.findAdministratorByUsername(username);
    }
    public String findCustomerSalt(String username){return customerDao.findCustomerSalt(username); }
    public String findAdministratorSalt(String username){return administratorDao.findAdministratorSalt(username); }

    public String findCustomerHashPassword(String username){return customerDao.findCustomerHashPassword(username);}
    public String findAdministratorHashPassword(String username){return administratorDao.findAdministratorHashPassword(username);}



}