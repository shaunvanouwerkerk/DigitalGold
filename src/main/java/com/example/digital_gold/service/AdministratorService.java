package com.example.digital_gold.service;

/**
 * @Author Shaun van Ouwerkerk
 */

import com.example.digital_gold.configuration.Config;
import com.example.digital_gold.domain.AdministratorDashboard;
import com.example.digital_gold.domain.BankAccount;
import com.example.digital_gold.repository.MapDatabase;
import com.example.digital_gold.repository.RootRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AdministratorService {

    private BankAccount bankAccount;
    private RootRepository rootRepository;
    private MapDatabase tokenDatabase;
    private Config config;


    @Autowired
    public AdministratorService(BankAccount bankAccount, RootRepository rootRepository, MapDatabase tokenDatabase, Config config) {
        this.bankAccount = bankAccount;
        this.rootRepository = rootRepository;
        this.tokenDatabase = tokenDatabase;
        this.config = config;
    }

    public double getStartingBudgetByUsername(String token){
        String username = tokenDatabase.findUserByToken(token);
        return rootRepository.findStartingBudgetByUsername(username);
    }

    public double getTransactionFeeByUsername(String token) {
        String username = tokenDatabase.findUserByToken(token);
        return rootRepository.findTransactionFeeByUsername(username);
    }

    public void updateStartingBudget(String token, Double startingBudget){
        String username = tokenDatabase.findUserByToken(token);
        Double transactionFee =rootRepository.findTransactionFeeByUsername(username);
        AdministratorDashboard administratorDashboard = new AdministratorDashboard(username,startingBudget,transactionFee);
        rootRepository.updateStartingBudget(administratorDashboard);
    }

    public void updateTransactionFee (String token, Double transactionFee){
        String username = tokenDatabase.findUserByToken(token);
        Double startingBudget =rootRepository.findStartingBudgetByUsername(username);
        AdministratorDashboard administratorDashboard = new AdministratorDashboard(username,startingBudget,transactionFee);
        rootRepository.updateTransactionFee(administratorDashboard);
    }




}

