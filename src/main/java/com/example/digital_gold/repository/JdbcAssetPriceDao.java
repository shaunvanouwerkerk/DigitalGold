package com.example.digital_gold.repository;

import com.example.digital_gold.domain.AssetPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * @author Fiona Gray
 * */

@Repository
public class JdbcAssetPriceDao implements AssetPriceDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcAssetPriceDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private PreparedStatement insertAssetPriceStatement(AssetPrice assetPrice, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into AssetPrice (assetCode, price, date) values (?, ?, ?)");
        preparedStatement.setString(1, assetPrice.getAsset().getAssetCode());
        preparedStatement.setDouble(2, assetPrice.getPrice());
        preparedStatement.setObject(3, assetPrice.getDate());
        return preparedStatement;
    }

    @Override
    public void saveAssetPrice(AssetPrice assetPrice) {
        jdbcTemplate.update(connection -> insertAssetPriceStatement(assetPrice, connection));
    }

    // todo throws SQLexception?
    @Override
    public AssetPrice findPriceByAssetCode(String assetCode) {
        String sql = "Select 1 from AssetPrice where assetCode = ?";
        return jdbcTemplate.queryForObject(sql, new JdbcAssetPriceDao.AssetPriceRowMapper(), assetCode);
    }

    // todo AssetList findAllAvailableAssets() methode schrijven met SQL view?

    // todo methode findAssetPriceByAssetCodeAndDate() schrijven?


    private static class AssetPriceRowMapper implements RowMapper<AssetPrice> {

        @Override
        public AssetPrice mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
            double price = resultSet.getDouble("price");
            LocalDate date = resultSet.getDate("date").toLocalDate();
            AssetPrice assetPrice = new AssetPrice(price, date);
            assetPrice.setAsset(null);
            return assetPrice;
        }
    }
}
