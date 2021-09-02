package com.example.digital_gold.repository;

import com.example.digital_gold.domain.Customer;
import com.example.digital_gold.domain.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RootRepository {

    private final Logger logger = LoggerFactory.getLogger(RootRepository.class);

    private CustomerDao customerDao;
    private TransactionDao transactionDao;

    @Autowired
    public RootRepository(CustomerDao customerDao,TransactionDao transactionDao) {
        this.customerDao = customerDao;
        this.transactionDao = transactionDao;
        logger.info("New RootRepository");
    }


    public Customer saveCustomer(Customer customer) {
        return customerDao.save(customer);
    }

    public boolean findCustomerByUsernameAndEmail(String username, String emailAddress) {
        return customerDao.findCustomerByUsernameAndEmail(username, emailAddress);
    }
    public String findCustomerSalt(String username){return customerDao.findCustomerSalt(username); }

    public String findCustomerHashPassword(String username){return customerDao.findCustomerHashPassword(username);}


    public Transaction saveTransaction(Transaction transaction){return transactionDao.saveTransaction(transaction);
    }

}