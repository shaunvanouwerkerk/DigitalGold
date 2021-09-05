package com.example.digital_gold.repository;

import com.example.digital_gold.domain.BankAccount;

public interface BankAccountDao {
    BankAccount saveBankAccount(BankAccount bankAccount);
    BankAccount updateBalance(BankAccount bankAccount);
    double getBalanceByIban(String iban);
}
