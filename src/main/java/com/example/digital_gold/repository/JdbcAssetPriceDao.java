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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author Fiona Gray
 * */

//TODO SQLexceptions!

@Repository
public class JdbcAssetPriceDao implements AssetPriceDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcAssetPriceDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private PreparedStatement insertAssetPriceStatement(AssetPrice assetPrice, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into AssetPrice (assetCode, datetime, price) values (?, now(), ?)");
        preparedStatement.setString(1, assetPrice.getAsset().getAssetCode());
        preparedStatement.setDouble(2, assetPrice.getPrice());
        return preparedStatement;
    }

    @Override
    public AssetPrice saveAssetPrice(AssetPrice assetPrice) {
        jdbcTemplate.update(connection -> insertAssetPriceStatement(assetPrice, connection));
        return assetPrice;
    }

    @Override
    public AssetPrice findAssetPriceByAssetCode(String assetCode) {
        String sql = "SELECT * FROM AssetPrice WHERE assetCode = ?" +
                "AND (Datetime) IN (SELECT Max(Datetime) FROM AssetPrice)";
        return jdbcTemplate.queryForObject(sql, new AssetPriceRowMapper(), assetCode);
    }

    @Override
    public List<AssetPrice> findAllAssetPrices() {
        String sql = "SELECT * FROM AssetPrice WHERE (Datetime) IN (SELECT Max(Datetime) FROM AssetPrice)";
        return jdbcTemplate.query(sql, new AssetPriceRowMapper());
    }


    private static class AssetPriceRowMapper implements RowMapper<AssetPrice> {

        @Override
        public AssetPrice mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
            Asset asset = null;
            double price = resultSet.getDouble("price");
            LocalDateTime dateTime = dateTimeFormatter(resultSet.getString("datetime"));
            AssetPrice assetPrice = new AssetPrice(asset, price, dateTime);
            return assetPrice;
        }
    }

    private static LocalDateTime dateTimeFormatter (String resultSet) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String sqlDateTime = resultSet;
        LocalDateTime dateTime = LocalDateTime.parse(sqlDateTime, formatter);
        return dateTime;
    }
}
