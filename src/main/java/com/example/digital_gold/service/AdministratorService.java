package com.example.digital_gold.service;

/**
 * @Author Shaun van Ouwerkerk
 */

import com.example.digital_gold.domain.BankAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AdministratorService {

    private BankAccount bankAccount;

    @Autowired
    public AdministratorService(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }



}

