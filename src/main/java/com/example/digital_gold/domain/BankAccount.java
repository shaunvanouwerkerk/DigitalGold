package com.example.digital_gold.domain;

import com.example.digital_gold.helper.IbanGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  @author Sandra Turina
 * */

public class BankAccount {
    private final Logger logger = LoggerFactory.getLogger(Customer.class);
    private double startingBudget = 1250.00; // TODO willen wij dat hier automatisch instellen?
    private String iban;
    private double balance; // TODO in dollars, positief, niet negatief

    public BankAccount() {
        IbanGenerator ibanGenerator = new IbanGenerator();
        this.iban = ibanGenerator.generateIban();
        this.balance = startingBudget;
    }

    public BankAccount(String iban, double balance) {
        this.iban = iban;
        this.balance = balance;
    }
    public BankAccount(String iban, double balance, double startingBudget) {
        this.iban = iban;
        this.balance = balance;
        this.startingBudget = startingBudget;
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

    @Override
    public String toString() {
        return "BankAccount{" +
                "iban='" + iban + '\'' +
                ", balance=" + balance +
                '}';
    }
}