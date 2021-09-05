package com.example.digital_gold.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

/*
@Author Jany Gaal
*/

@Repository
public class JdbcPortfolioDao implements PortfolioDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcPortfolioDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int addPortfolioAsset(PortfolioDatabase portfolioDatabase) {
        return jdbcTemplate.update(connection -> insertPortfolioAssetStatement(portfolioDatabase, connection));
    }

    private PreparedStatement insertPortfolioAssetStatement(PortfolioDatabase portfolioDatabase,
                                                            Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into portfolio (username, assetCode, amount) values (?,?,?)");
        preparedStatement.setString(1, portfolioDatabase.getUsername());
        preparedStatement.setString(2, portfolioDatabase.getAssetCode());
        preparedStatement.setDouble(3, portfolioDatabase.getAmount());
        return preparedStatement;
    }

    @Override
    public PortfolioDatabase updatePortfolioAsset(PortfolioDatabase portfolioDatabase) {
        jdbcTemplate.update(connection -> updatePortfolioAssetStatement(portfolioDatabase, connection));
        return portfolioDatabase;
    }

    private PreparedStatement updatePortfolioAssetStatement(PortfolioDatabase portfolioDatabase,
                                                            Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE portfolio Set amount = ? WHERE username = ? AND assetCode = ?");
        preparedStatement.setDouble(1, portfolioDatabase.getAmount());
        preparedStatement.setString(2, portfolioDatabase.getUsername());
        preparedStatement.setString(3, portfolioDatabase.getAssetCode());
        return preparedStatement;
    }

    @Override
    public int deletePortfolioAsset(PortfolioDatabase portfolioDatabase) {
        return jdbcTemplate.update(connection -> deletePortfolioAssetStatement(portfolioDatabase, connection));
    }

    private PreparedStatement deletePortfolioAssetStatement(PortfolioDatabase portfolioDatabase,
                                                            Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM portfolio WHERE username = ? AND assetCode = ?;");
        preparedStatement.setString(1, portfolioDatabase.getUsername());
        preparedStatement.setString(2, portfolioDatabase.getAssetCode());
        return preparedStatement;
    }

    @Override
    public List<PortfolioDatabase> getPortfolioAssetsByUsername(String username) {
        return jdbcTemplate.query("SELECT * FROM portfolio WHERE username = ?", new PortfolioRowMapper(), username);
    }

    @Override
    public List<String> getAllUsersWithAPortfolio() {
        return jdbcTemplate.queryForList("SELECT DISTINCT userName FROM portfolio;",String.class);
    }

    private static class PortfolioRowMapper implements RowMapper<PortfolioDatabase> {
        @Override
        public PortfolioDatabase mapRow(ResultSet resultSet, int i) throws SQLException {
            PortfolioDatabase portfolioDatabase = new PortfolioDatabase();
            portfolioDatabase.setUsername(resultSet.getString("username"));
            portfolioDatabase.setAssetCode(resultSet.getString("assetcode"));
            portfolioDatabase.setAmount(resultSet.getDouble("amount"));
            return portfolioDatabase;
        }
    }

     public static class PortfolioDatabase {
        String username;
        String assetCode;
        double amount;

         public PortfolioDatabase() {
         }

        public String getUsername() {
            return username;
        }

        public String getAssetCode() {
            return assetCode;
        }

        public double getAmount() {
            return amount;
        }

         public void setUsername(String username) {
             this.username = username;
         }

         public void setAssetCode(String assetCode) {
             this.assetCode = assetCode;
         }

         public void setAmount(double amount) {
             this.amount = amount;
         }

         public PortfolioDatabase(String username, String assetCode, double amount) {
             this.username = username;
             this.assetCode = assetCode;
             this.amount = amount;
         }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof PortfolioDatabase)) return false;
            PortfolioDatabase that = (PortfolioDatabase) o;
            return Double.compare(that.getAmount(), getAmount()) == 0 && Objects.equals(getUsername(), that.getUsername()) && Objects.equals(getAssetCode(), that.getAssetCode());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getUsername(), getAssetCode(), getAmount());
        }

        @Override
        public String toString() {
            return "PortfolioDatabase{" +
                    "username='" + username + '\'' +
                    ", assetCode='" + assetCode + '\'' +
                    ", amount=" + amount +
                    '}';
        }
    }
}
