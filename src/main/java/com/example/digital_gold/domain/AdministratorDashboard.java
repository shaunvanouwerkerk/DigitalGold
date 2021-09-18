package com.example.digital_gold.domain;

/**
 * @Author Shaun van Ouwerkerk
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
@Component
public class AdministratorDashboard {

    private final Logger logger = LoggerFactory.getLogger(AdministratorDashboard.class);

    private String username;
    private double startingBudget;
    private double transactionFee;

    public AdministratorDashboard() {
    }

    public AdministratorDashboard(String username, double startingBudget, double transactionFee) {
        this.username = username;
        this.startingBudget = startingBudget;
        this.transactionFee = transactionFee;
    }

    public String getUsername() {
        return username;
    }

    public double getStartingBudget() {
        return startingBudget;
    }

    public double getTransactionFee() {
        return transactionFee;
    }

    @Override
    public String toString() {
        return "AdministratorDashboard{" +
                "username='" + username + '\'' +
                ", startingBudget=" + startingBudget +
                ", transactionFee=" + transactionFee +
                '}';
    }
}
