package com.example.digital_gold.repository;

import com.example.digital_gold.domain.Address;
import com.example.digital_gold.domain.Customer;
import com.example.digital_gold.domain.CustomerDetails;
import com.example.digital_gold.domain.FullName;
import org.springframework.jdbc.core.RowMapper;
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
                        "houseNumber, streetName, zipCode, city, emailAddress, salt, iban, status) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,? )"
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
        preparedStatement.setString(14, customer.getCustomerDetails().getIban());
        preparedStatement.setBoolean(15,customer.isStatus());

        return preparedStatement;
    }

    @Override
    public Customer save(Customer customer) {
        jdbcTemplate.update(connection -> insertCustomerStatement(customer, connection));
        return customer;
    }
    /**
     * @author David Truijens
     * */
    @Override
    public String findUsernameByIban(String iban) {
        String sql = "SELECT username FROM customer WHERE iban = ?";
        return jdbcTemplate.queryForObject(sql,new Object[]{iban}, String.class);
    }

    /**
    * @author Fiona Gray
    * */
    @Override
    public boolean findCustomerByUsernameAndEmail(String username, String emailAddress) {
        return ((findCustomerByUsername(username)) || (findCustomerByEmailAddress(emailAddress)));
    }

    public boolean findCustomerByUsername(String username) {
        return jdbcTemplate.queryForObject("SELECT EXISTS(SELECT 1 FROM customer WHERE username = ?)", Boolean.class, username);
    }

    public boolean findCustomerByEmailAddress(String emailAddress) {
        return jdbcTemplate.queryForObject("SELECT EXISTS(SELECT 1 FROM customer WHERE emailAddress = ?)", Boolean.class, emailAddress);
    }

    @Override
    public String findCustomerSalt(String username) {
        String sql = "SELECT salt FROM customer WHERE username = ?";
        return jdbcTemplate.queryForObject(sql,new Object[]{username}, String.class);
    }

    @Override
    public String findCustomerHashPassword(String username) {
        String sql = "SELECT password FROM customer WHERE username = ?";
        return jdbcTemplate.queryForObject(sql,new Object[]{username}, String.class);
    }
    @Override
    public Customer findAndReturnCustomerByUsername (String username){
        String sql = "SELECT * FROM customer WHERE username = ?";
        return jdbcTemplate.queryForObject(sql, new CustomerRowMapper(), username);
    }
    private static class CustomerRowMapper implements RowMapper<Customer>{

        @Override
        public Customer mapRow(ResultSet resultSet, int i) throws SQLException {
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String firstname = resultSet.getString("firstname");
            String prefix = resultSet.getString("prefix");
            String lastname = resultSet.getString("lastname");
            Date dateOfBirth = resultSet.getDate("dateOfBirth");
            String bsn = resultSet.getString("bsn");
            Integer houseNumber = resultSet.getInt("housenumber");
            String streetName = resultSet.getString("streetName");
            String zipcode = resultSet.getString("zipcode");
            String city = resultSet.getString("city");
            String emailAddress = resultSet.getString("emailaddress");
            String salt = resultSet.getString("salt");
            String iban = resultSet.getString("iban");
            Boolean status = resultSet.getBoolean("status");
            FullName fullname= new FullName(firstname,prefix,lastname);
            Address address = new Address(houseNumber,streetName,zipcode,city);
            CustomerDetails customerDetails = new CustomerDetails(dateOfBirth,bsn,emailAddress,iban);
            Customer customer = new Customer(username,password,salt,status,fullname,address,customerDetails);

            return customer;
        }
    }



    }

