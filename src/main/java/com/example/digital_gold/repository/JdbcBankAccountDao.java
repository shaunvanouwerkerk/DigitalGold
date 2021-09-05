package com.example.digital_gold.repository;

import com.example.digital_gold.domain.Asset;
import com.example.digital_gold.domain.BankAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *  @author Sandra Turina
 * */

@Repository
public class JdbcBankAccountDao implements BankAccountDao{

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcBankAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private PreparedStatement insertBankAccountStatement(BankAccount bankAccount, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into BankAccount (iban, balance) values (?, ?)");
        preparedStatement.setString(1, bankAccount.getIban());
        preparedStatement.setDouble(2, bankAccount.getBalance());
        return preparedStatement;
    }

    @Override
    public BankAccount saveBankAccount(BankAccount bankAccount) {
        jdbcTemplate.update(connection -> insertBankAccountStatement(bankAccount, connection));
        return bankAccount;
    }

    private PreparedStatement updateBalanceStatement(BankAccount bankAccount, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE BankAccount Set balance = ? WHERE iban = ?");
        preparedStatement.setDouble(1, bankAccount.getBalance());
        preparedStatement.setString(2, bankAccount.getIban());
        return preparedStatement;
    }

    // todo als deze iban niet bestaat gebeurt niets, ook geen foutmelding
    @Override
    public BankAccount updateBalance(BankAccount bankAccount) {
        jdbcTemplate.update(connection -> updateBalanceStatement(bankAccount, connection));
        return bankAccount;
    }


    @Override
    public double getBalanceByIban(String iban) {
        String sql= "SELECT balance FROM bankAccount WHERE iban = ?";
        return jdbcTemplate.queryForObject(sql,new Object[]{iban}, Double.class);
    }



}
