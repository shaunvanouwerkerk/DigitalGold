package com.example.digital_gold.repository;

import com.example.digital_gold.domain.PortfolioHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/*
@Author Jany Gaal
*/

@Repository
public class JdbcPortfolioHistoryDao implements PortfolioHistoryDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcPortfolioHistoryDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public int savePortfolioValue(PortfolioHistory portfolioHistory) {
        return  jdbcTemplate.update(connection -> insertPortfolioHistoryStatement(portfolioHistory, connection));
    }

    private PreparedStatement insertPortfolioHistoryStatement(PortfolioHistory portfolioHistory, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("insert into portfoliohistory (userName, date, totalValue) VALUES (?,?,?)");
        preparedStatement.setString(1, portfolioHistory.getCustomer().getUsername());
        preparedStatement.setString(2, String.valueOf(portfolioHistory.getDate()));
        preparedStatement.setDouble(3, portfolioHistory.getTotalvalue());
        return preparedStatement;
    }

    @Override
    public List<PortfolioHistory> getPortfolioValuesByUserName(String username) {
        return jdbcTemplate.query("SELECT * FROM portfoliohistory WHERE username = ?", new PortfolioRowMapper(), username);
    }

    private static class PortfolioRowMapper implements RowMapper<PortfolioHistory> {
        @Override
        public PortfolioHistory mapRow(ResultSet resultSet, int i) throws SQLException{
            PortfolioHistory portfolioHistory = new PortfolioHistory();
            portfolioHistory.setCustomer(null);
            portfolioHistory.setDate(LocalDate.parse(resultSet.getString("date")));
            portfolioHistory.setTotalvalue(resultSet.getDouble("totalvalue"));
            return portfolioHistory;
        }
    }
}
