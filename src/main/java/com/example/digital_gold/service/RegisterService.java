package com.example.digital_gold.service;

import com.example.digital_gold.domain.Administrator;
import com.example.digital_gold.domain.BankAccount;
import com.example.digital_gold.configuration.Config;
import com.example.digital_gold.domain.Customer;
import com.example.digital_gold.helper.SaltMaker;
import com.example.digital_gold.repository.RootRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RegisterService {

    private RootRepository rootRepository;
    private SaltMaker saltMaker;
    private HashService hashService;
    private Config config;

    private final Logger logger = LoggerFactory.getLogger(RegisterService.class);

    @Autowired
    public RegisterService(RootRepository rootRepository, SaltMaker saltMaker, HashService hashService, Config config) {
        this.rootRepository = rootRepository;
        this.saltMaker = saltMaker;
        this.hashService = hashService;
        this.config = config;
        logger.info("New RegisterService");
    }

    // TODO: if-statement en methodes refactoren op basis van front end (email en username op andere pagina gechecked?)
    public Customer register(Customer customer) {
        String salt = saltMaker.generateSalt();
        customer.setSalt(salt);
        String hashPassword = customer.getPassword() + salt;
        customer.setPassword(hashService.hash(hashPassword));
        customer.getCustomerDetails().setIban(createBankAccount());
        if (rootRepository.findCustomerByUsernameAndEmail(customer.getUsername(), customer.getCustomerDetails().getEmailaddress())) {
            return null;
        } else {
            rootRepository.saveCustomer(customer);
            return customer;
        }
    }
    public Administrator register(Administrator administrator) {
        String salt = saltMaker.generateSalt();
        administrator.setSalt(salt);
        String hashPassword = administrator.getPassword() + salt;
        administrator.setPassword(hashService.hash(hashPassword));
        if (rootRepository.findAdministratorByUsername(administrator.getUsername())){
            return null;
        } else {
            rootRepository.saveAdministrator(administrator);
            return administrator;
        }
    }

    public String createBankAccount() {
        BankAccount newBankAccount = new BankAccount(config);//TODO SHAUN AANGEPAST KIJKEN OF DIT NOG WERK
        rootRepository.saveBankAccount(newBankAccount);
        return newBankAccount.getIban();
    }

    public RootRepository getRootRepository() {
        return rootRepository;
    }
}
