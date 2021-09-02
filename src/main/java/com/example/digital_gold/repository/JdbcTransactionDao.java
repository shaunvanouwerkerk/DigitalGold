package com.example.digital_gold.repository;


import com.example.digital_gold.domain.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

/**
 @author David Truijens
 */
@Repository
public class JdbcTransactionDao implements TransactionDao{

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTransactionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private PreparedStatement insertTransactionStatement(Transaction transaction, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into transaction (assetCode, ibanSell, ibanBuy, amount, sellingPrice, transactionCost, date) VALUES " +
                        "(?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, transaction.getAssetCode());
        preparedStatement.setString(2, transaction.getIbanSell());
        preparedStatement.setString(3, transaction.getIbanBuy());
        preparedStatement.setDouble(4,transaction.getAssetAmount());
        preparedStatement.setDouble(5,transaction.getAssetPrice());
        preparedStatement.setDouble(6,transaction.getTransactionFee());
        preparedStatement.setString(7, DateTimeFormatter.ISO_DATE_TIME.format(transaction.getTransactionDate()));
        return preparedStatement;
    }

    public Transaction saveTransaction(Transaction transaction) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> insertTransactionStatement(transaction,connection), keyHolder);
        int key = keyHolder.getKey().intValue();
        transaction.setTransactionId(key);
        return transaction;
    }

    //TODO: getTransactionsByIban
    public List<Transaction> findTransactionsByIban(String iban) {
        String sql = "select * from transaction where ibanSell = ? OR ibanBuy = ?";
        return jdbcTemplate.query(sql,new Object[]{iban},(rs,rowNum)-> new Transaction(
                rs.getInt("transactionId"),
                rs.getObject("date",LocalDateTime.class),
                rs.getString("assetCode"),
                rs.getDouble("amount"),
                rs.getDouble("sellingPrice"),
                rs.getDouble("transactionCost"),
                rs.getString("ibanSell"),
                rs.getString("ibanBuy")
                )
        );
    }



}
