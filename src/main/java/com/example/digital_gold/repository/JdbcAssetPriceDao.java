package com.example.digital_gold.repository;

import com.example.digital_gold.domain.Asset;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                "insert into AssetPrice (assetCode, date, price) values (?, ?, ?)");
        preparedStatement.setString(1, assetPrice.getAsset().getAssetCode());
        preparedStatement.setObject(2, assetPrice.getDate());
        preparedStatement.setDouble(3, assetPrice.getPrice());
        return preparedStatement;
    }

    @Override
    public AssetPrice saveAssetPrice(AssetPrice assetPrice) {
        jdbcTemplate.update(connection -> insertAssetPriceStatement(assetPrice, connection));
        return assetPrice;
    }

    //TODO throws SQLexception?
    @Override
    public AssetPrice findPriceByAssetCode(String assetCode) {
        String sql = "Select * from AssetPrice where assetCode = ?";
        return jdbcTemplate.queryForObject(sql, new AssetPriceRowMapper(), assetCode);
    }

    //TODO AssetList findAllAvailableAssets() methode schrijven met SQL view?
    @Override
    public  List<AssetPrice> findAllAvailableAssets(LocalDate today) {
        //String sql = "create view assetOverview AS (select assetCode, price from assetPrice where date = ?)";
        //return jdbcTemplate.query(sql, new AssetPriceRowMapper(), today);
        String sql = "select * from assetPrice where date = ?";
        return jdbcTemplate.query(sql, new AssetPriceRowMapper(), today);
    }


    //TODO methode findAssetPriceByAssetCodeAndDate() schrijven?


    private static class AssetPriceRowMapper implements RowMapper<AssetPrice> {

        @Override
        public AssetPrice mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
            Asset asset = null;
            double price = resultSet.getDouble("price");
            LocalDate date = LocalDate.parse(resultSet.getString("date"));
            AssetPrice assetPrice = new AssetPrice(asset, price, date);
            return assetPrice;
        }
    }
}
