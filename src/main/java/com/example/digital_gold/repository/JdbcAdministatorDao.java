package com.example.digital_gold.repository;

import com.example.digital_gold.domain.Administrator;
import com.example.digital_gold.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class JdbcAdministatorDao implements AdministratorDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcAdministatorDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private PreparedStatement insertCustomerStatement(Administrator administrator, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into administrator (username, password,  salt) values (?, ?, ?)"
        );
        preparedStatement.setString(1, administrator.getUsername());
        preparedStatement.setString(2, administrator.getPassword());
        preparedStatement.setString(3, administrator.getSalt());

        return preparedStatement;
    }

    @Override
    public Administrator save(Administrator administrator) {
        jdbcTemplate.update(connection -> insertCustomerStatement(administrator, connection));
        return administrator;
    }

    @Override
    public String findAdministratorSalt(String username) {
        String sql = "SELECT salt FROM administrator WHERE username = ?";
        return jdbcTemplate.queryForObject(sql,new Object[]{username}, String.class);
    }

    @Override
    public String findAdministratorHashPassword(String username) {
        String sql = "SELECT password FROM administrator WHERE username = ?";
        return jdbcTemplate.queryForObject(sql,new Object[]{username}, String.class);
    }
    @Override
    public boolean findAdministratorByUsername(String username) {
        return jdbcTemplate.queryForObject("SELECT EXISTS(SELECT 1 FROM administrator WHERE username = ?)", Boolean.class, username);
    }


}
