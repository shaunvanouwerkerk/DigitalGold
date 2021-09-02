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
                "insert into customer (username, password, firstName, prefix, lastName, dateOfBirth, bsn, " +
                        "houseNumber, streetName, zipCode, city, emailAddress, salt) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,? )"
        );
        preparedStatement.setString(1, customer.getUsername());
        preparedStatement.setString(2, customer.getPassword());
        preparedStatement.setString(3, customer.getFullName().getFirstName());
        preparedStatement.setString(4, customer.getFullName().getPrefix());
        preparedStatement.setString(5, customer.getFullName().getLastName());
        preparedStatement.setString(6, String.valueOf(customer.getCustomerDetails().getDateOfBirth()));
        preparedStatement.setString(7, customer.getCustomerDetails().getBsn());
        preparedStatement.setInt(8, customer.getAddress().getHouseNumber());
        preparedStatement.setString(9, customer.getAddress().getStreetName());
        preparedStatement.setString(10, customer.getAddress().getZipCode());
        preparedStatement.setString(11, customer.getAddress().getCity());
        preparedStatement.setString(12, customer.getCustomerDetails().getEmailaddress());
        preparedStatement.setString(13, customer.getSalt());
        return preparedStatement;
    }

    @Override
    public Customer save(Customer customer) {
        jdbcTemplate.update(connection -> insertCustomerStatement(customer, connection));
        return customer;
    }

    /**
    * @author Fiona Gray
    * */
    @Override
    public boolean findCustomerByUsernameAndEmail(String username, String emailAddress) {
        return ((findCustomerByUsername(username)) || (findCustomerByEmailAddress(emailAddress)));
    }

    public boolean findCustomerByUsername(String username) {
        return jdbcTemplate.queryForObject("SELECT EXISTS(SELECT 1 FROM customer_table WHERE username = ?)", Boolean.class, username);
    }

    public boolean findCustomerByEmailAddress(String emailAddress) {
        return jdbcTemplate.queryForObject("SELECT EXISTS(SELECT 1 FROM customer_table WHERE emailAddress = ?)", Boolean.class, emailAddress);
    }

    /**
     * @Author Shaun
     */
    @Override
    public String findCustomerSalt(String username) {
        String sql = "SELECT salt FROM customer_table WHERE username = ?";
        return jdbcTemplate.queryForObject(sql,new Object[]{username}, String.class);
    }

    @Override
    public String findCustomerHashPassword(String username) {
        String sql = "SELECT password FROM customer_table WHERE username = ?";
        return jdbcTemplate.queryForObject(sql,new Object[]{username}, String.class);
    }
}
