package com.example.digital_gold.domain;

import com.example.digital_gold.configuration.Config;
import com.example.digital_gold.helper.IbanGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *  @author Sandra Turina
 * */
@Component
public class BankAccount {
    private final Logger logger = LoggerFactory.getLogger(Customer.class);
    private double startingBudget;
    private String iban;
    private double balance; // TODO in dollars, positief, niet negatief


    @Autowired
    public BankAccount(Config config) {
        IbanGenerator ibanGenerator = new IbanGenerator();
        // TODO Haalt startingBudget uit de properties maar moet nog uit config komen
        startingBudget = config.getStartingBudget();
        this.iban = ibanGenerator.generateIban();
        this.balance = startingBudget;

    }

    public BankAccount(String iban, double balance) {
        this.iban = iban;
        this.balance = balance;
    }


    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getStartingBudget() {
        return startingBudget;
    }

    public void setStartingBudget(double startingBudget) {
        this.startingBudget = startingBudget;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "iban='" + iban + '\'' +
                ", balance=" + balance +
                '}';
    }
}