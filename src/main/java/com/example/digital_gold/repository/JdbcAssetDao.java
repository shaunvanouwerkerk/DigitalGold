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

    private PreparedStatement insertAssetStatement(Asset asset, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into Asset (assetCode, assetName, description) values (?, ?, ?)");
        preparedStatement.setString(1, asset.getAssetCode());
        preparedStatement.setString(2, asset.getAssetName());
        preparedStatement.setString(3, asset.getDescription());
        return preparedStatement;
    }

    @Override
    public void saveAsset(Asset asset) {
        jdbcTemplate.update(connection -> insertAssetStatement(asset, connection));
    }

    // todo throws SQLexception?
    @Override
    public Asset findByAssetCode(String assetCode) {
        String sql = "Select 1 from Asset where assetCode = ?";
        return jdbcTemplate.queryForObject(sql, new AssetRowMapper(), assetCode);
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
