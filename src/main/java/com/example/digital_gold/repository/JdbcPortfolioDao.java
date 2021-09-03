package com.example.digital_gold.repository;

import com.example.digital_gold.domain.Portfolio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/*
@Author Jany Gaal
*/

@Repository
public class JdbcPortfolioDao implements PortfolioDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcPortfolioDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private PreparedStatement insertPortfolioAssetStatement(RootRepository.PortfolioDatabase portfolioDatabase,
                                                            Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into portfolio_table (username, assetCode, amount) values (?,?,?)");
        preparedStatement.setString(1, portfolioDatabase.getUsername());
        preparedStatement.setString(2, portfolioDatabase.getAssetCode());
        preparedStatement.setDouble(3, portfolioDatabase.getAmount());
        return preparedStatement;
    }

    private PreparedStatement updatePortfolioAssetStatement(RootRepository.PortfolioDatabase portfolioDatabase,
                                                            Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE portfolio_table Set amount = ? WHERE username = ? AND assetCode = ?");
        preparedStatement.setDouble(1, portfolioDatabase.getAmount());
        preparedStatement.setString(2, portfolioDatabase.getUsername());
        preparedStatement.setString(3, portfolioDatabase.getAssetCode());
        return preparedStatement;
    }

    private PreparedStatement deletePortfolioAssetStatement(RootRepository.PortfolioDatabase portfolioDatabase,
                                                            Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM portfolio_table WHERE username = ? AND assetCode = ?;");
        preparedStatement.setString(1, portfolioDatabase.getUsername());
        preparedStatement.setString(2, portfolioDatabase.getAssetCode());
        return preparedStatement;
    }


    @Override
    public RootRepository.PortfolioDatabase addPortfolioAsset(RootRepository.PortfolioDatabase portfolioDatabase) {
        jdbcTemplate.update(connection -> insertPortfolioAssetStatement(portfolioDatabase, connection));
        return portfolioDatabase;
    }

    @Override
    public RootRepository.PortfolioDatabase updatePortfolioAsset(RootRepository.PortfolioDatabase portfolioDatabase) {
        jdbcTemplate.update(connection -> updatePortfolioAssetStatement(portfolioDatabase, connection));
        return portfolioDatabase;
    }


    @Override
    public RootRepository.PortfolioDatabase deletePortfolioAsset(RootRepository.PortfolioDatabase portfolioDatabase) {
        jdbcTemplate.update(connection -> deletePortfolioAssetStatement(portfolioDatabase, connection));
        return null;
    }

    @Override
    public RootRepository.PortfolioDatabase getPortfolioAssetsByUsername(String username) {
/*        List<RootRepository.PortfolioDatabase> portfolioList =
        jdbcTemplate.query("SELECT * FROM portfolio_table WHERE username = ?", new assetRowMapper(), username);
        if (portfolioList.size() == 1) {
            return portfolioList.get(0);
        }*/
        return null;
    }

    @Override
    public RootRepository.PortfolioDatabase getPortfolioAssetsPortfolioAsset(String username) {
        return null;
    }


/*    private static class assetRowMapper implements RowMapper<Portfolio> {

        @Override
        public Portfolio mapRow(ResultSet resultSet, int i) throws SQLException {
            Customer customer = null;

            Asset asset = resultSet.getString("assetcode");
            double amount = resultSet.getDouble("amount");

            return new Portfolio(customer , asset, amount);
        }

    }*/
}
