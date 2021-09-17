package com.example.digital_gold.service;

import com.example.digital_gold.domain.BankAccount;
import com.example.digital_gold.repository.RootRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Sandra Turina
 */
@Service
public class BankAccountOverviewService {

    private RootRepository rootRepository;

    private final Logger logger = LoggerFactory.getLogger(BankAccountOverviewService.class);

    @Autowired
    public BankAccountOverviewService(RootRepository rootRepository) {
        this.rootRepository = rootRepository;
    }

    public BankAccount bankAccountOverview(String username) {
            String iban = rootRepository.findIbanByUsername(username);
            double balance = rootRepository.getBalanceByIban(iban);
            BankAccount bankAccount = new BankAccount(iban, balance);
            return bankAccount;
    }
}
