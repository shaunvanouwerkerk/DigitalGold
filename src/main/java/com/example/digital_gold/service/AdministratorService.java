package com.example.digital_gold.service;

/**
 * @Author Shaun van Ouwerkerk
 */

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
    private AdministratorDashboard administratorDashboard;

    @Autowired
    public AdministratorService(BankAccount bankAccount, RootRepository rootRepository, MapDatabase tokenDatabase, AdministratorDashboard administratorDashboard) {
        this.bankAccount = bankAccount;
        this.rootRepository = rootRepository;
        this.tokenDatabase = tokenDatabase;
    }
    //METHODE VIA CONFIG
    public double getStartingBudget(){
        double startingBudget = bankAccount.getStartingBudget();
        return startingBudget;
    }
    // METHODE VIA CONFIG
    public double setStartingBudget(double startingBudget) {
        bankAccount.setStartingBudget(startingBudget);
        double startingBudgetAdjusted = bankAccount.getStartingBudget();
        return startingBudgetAdjusted;
    }
    // METHODE VIA DATABASE
    public double getStartingBudgetByUsername(String token){
        String username = tokenDatabase.findUserByToken(token);
        return rootRepository.findStartingBudgetByUsername(username);
    }
    // METHODE VIA DATABASE
    public double getTransactionFeeByUsername(String token) {
        String username = tokenDatabase.findUserByToken(token);
        return rootRepository.findTransactionFeeByUsername(username);
    }
    // METHODE VIA DATABASE
    public void updateStartingBudget(String token, Double startingBudget){
        String username = tokenDatabase.findUserByToken(token);
        Double transactionFee =rootRepository.findTransactionFeeByUsername(username);
        AdministratorDashboard administratorDashboard = new AdministratorDashboard(username,startingBudget,transactionFee);
        rootRepository.updateStartingBudget(administratorDashboard);
    }
    // METHODE VIA DATABASE
    public void updateTransactionFee (String token, Double transactionFee){
        String username = tokenDatabase.findUserByToken(token);
        Double startingBudget =rootRepository.findStartingBudgetByUsername(username);
        AdministratorDashboard administratorDashboard = new AdministratorDashboard(username,startingBudget,transactionFee);
        rootRepository.updateTransactionFee(administratorDashboard);
    }




}

