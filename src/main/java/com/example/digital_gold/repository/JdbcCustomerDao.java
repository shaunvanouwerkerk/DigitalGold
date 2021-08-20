package com.example.digital_gold.repository;

import com.example.digital_gold.domain.Customer;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.logging.Logger;

@Repository
public class JdbcCustomerDao implements CustomerDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcCustomerDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private PreparedStatement insertCustomerStatement(Customer customer, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into customer_table (username, password, firstname, prefix, lastname, dateofbirth, bsn, " +
                        "housenumber, streetname, zipcode, city) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )"
        );
        preparedStatement.setString(1, customer.getUsername());
        preparedStatement.setString(2, customer.getPassword());
        preparedStatement.setString(3, customer.getFirstName());
        preparedStatement.setString(4, customer.getPrefix());
        preparedStatement.setString(5, customer.getLastName());
        preparedStatement.setString(6, String.valueOf(customer.getDateOfBirth()));
        preparedStatement.setInt(7, customer.getBsn());
        preparedStatement.setInt(8, customer.getHouseNumber());
        preparedStatement.setString(9, customer.getStreetName());
        preparedStatement.setString(10, customer.getZipCode());
        preparedStatement.setString(11, customer.getCity());
        return preparedStatement;
    }

    @Override
    public Customer save(Customer customer) {
        jdbcTemplate.update(connection -> insertCustomerStatement(customer, connection));
        return customer;
    }

    @Override
    public Customer findByUsername(String username) {
        return null;
    }
}
