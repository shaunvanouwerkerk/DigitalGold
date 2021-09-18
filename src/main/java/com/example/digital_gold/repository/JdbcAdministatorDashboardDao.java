package com.example.digital_gold.repository;
/**
 * @Author Shaun van Ouwerkerk
 */

import com.example.digital_gold.domain.AdministratorDashboard;
import com.example.digital_gold.domain.BankAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class JdbcAdministatorDashboardDao implements AministratorDashboardDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcAdministatorDashboardDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private PreparedStatement insertAdministratorDashboardStatement(AdministratorDashboard administratorDashboard, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into administrator (username, startingbudget,  transactionfee) values (?, ?, ?)"
        );
        preparedStatement.setString(1, administratorDashboard.getUsername());
        preparedStatement.setDouble(2, administratorDashboard.getStartingBudget());
        preparedStatement.setDouble(3, administratorDashboard.getTransactionFee());

        return preparedStatement;
    }

    @Override
    public AdministratorDashboard save(AdministratorDashboard administratorDashboard) {
        jdbcTemplate.update(connection -> insertAdministratorDashboardStatement(administratorDashboard,connection));
        return administratorDashboard;
    }

    @Override
    public Double findStartingBudgetByUsername(String username) {
        String sql = "SELECT startingbudget FROM administratorDashboard WHERE username = ?";
        return jdbcTemplate.queryForObject(sql,new Object[]{username}, Double.class);
    }

    @Override
    public Double findTransactionFeeByUsername(String username) {
        String sql = "SELECT transactionFee FROM administratorDashboard WHERE username = ?";
        return jdbcTemplate.queryForObject(sql,new Object[]{username}, Double.class);
    }

    @Override
    public AdministratorDashboard updateStartingBudget(AdministratorDashboard administratorDashboard) {
        jdbcTemplate.update(connection -> updateStartingBudgetStatement(administratorDashboard, connection));
        return administratorDashboard;
    }

    @Override
    public AdministratorDashboard updateTransactionFee(AdministratorDashboard administratorDashboard) {
        jdbcTemplate.update(connection -> updateTransactionFeeStatement(administratorDashboard, connection));
        return administratorDashboard;
    }


    private PreparedStatement updateTransactionFeeStatement(AdministratorDashboard administratorDashboard, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE AdministratorDashboard Set transactionFee = ? WHERE username = ?");
        preparedStatement.setDouble(1, administratorDashboard.getTransactionFee());
        preparedStatement.setString(2, administratorDashboard.getUsername());
        return preparedStatement;
    }

    private PreparedStatement updateStartingBudgetStatement(AdministratorDashboard administratorDashboard, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE AdministratorDashboard Set startingBudget = ? WHERE username = ?");
        preparedStatement.setDouble(1, administratorDashboard.getStartingBudget());
        preparedStatement.setString(2, administratorDashboard.getUsername());
        return preparedStatement;
    }
}

