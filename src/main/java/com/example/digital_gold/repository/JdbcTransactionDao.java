package com.example.digital_gold.repository;


import com.example.digital_gold.domain.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

/**
 @author David Truijens
 */
@Repository
public class JdbcTransactionDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTransactionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private PreparedStatement insertTransactionStatement(Transaction transaction, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into transaction (assetCode, ibanSell, ibanBuy, amount, sellingPrice, transactionCost, date) VALUES " +
                        "(?,?,?,?,?,?,?)");
        preparedStatement.setString(1, transaction.getAssetCode());
        preparedStatement.setString(2, transaction.getIbanSell());
        preparedStatement.setString(3, transaction.getIbanBuy());
        preparedStatement.setDouble(4,transaction.getAssetAmount());
        preparedStatement.setDouble(5,transaction.getAssetPrice());
        preparedStatement.setDouble(6,transaction.getTransactionFee());
        preparedStatement.setString(7, DateTimeFormatter.ISO_DATE_TIME.format(transaction.getTransactionDate()));

        return preparedStatement;
    }









}
