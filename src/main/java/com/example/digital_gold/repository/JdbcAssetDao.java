package com.example.digital_gold.repository;

import com.example.digital_gold.domain.Asset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Fiona Gray
* */

@Repository
public class JdbcAssetDao implements AssetDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcAssetDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //todo throws SQLexception?
    @Override
    public Asset findAssetByAssetCode(String assetCode) {
        String sql = "Select * from Asset where assetCode = ?";
        return jdbcTemplate.queryForObject(sql, new AssetRowMapper(), assetCode);
    }

    @Override
    public List<Asset> findAllAssets() {
        String sql = "Select * from Asset";
        return jdbcTemplate.query(sql, new AssetRowMapper());
    }


    private static class AssetRowMapper implements RowMapper<Asset> {

        @Override
        public Asset mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
            String assetCode = resultSet.getString("assetCode");
            String assetName = resultSet.getString("assetName");
            String description = resultSet.getString("description");
            Asset asset = new Asset(assetCode, assetName, description);
            return asset;
        }
    }
}
