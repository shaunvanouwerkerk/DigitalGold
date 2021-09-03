package com.example.digital_gold.repository;

import com.example.digital_gold.domain.Asset;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

/**
* @author Fiona Gray
* */

@SpringBootTest
@ActiveProfiles("test")
class JdbcAssetDaoTest {

    private AssetDao assetDaoTest;

    @Autowired
    public JdbcAssetDaoTest(AssetDao assetDaoTest) {
        this.assetDaoTest = assetDaoTest;
    }

    @Test
    public void assetDaoNotNull() {
        assertThat(assetDaoTest).isNotNull();
    }

    @Test
    public void saveAsset() {
        Asset testAsset = new Asset("FIE", "FieCoin", "popular cryptocurrency");
        Asset actualAsset = assetDaoTest.saveAsset(testAsset);
        assertThat(actualAsset).isEqualTo(testAsset);
    }

    @Test
    public void findByAssetCode() {
        Asset actual = assetDaoTest.findByAssetCode("BTC");
        Asset expected = new Asset("BTC", "Bitcoin", "Beschrijving");
        assertThat(actual).isEqualTo(expected);
    }

}