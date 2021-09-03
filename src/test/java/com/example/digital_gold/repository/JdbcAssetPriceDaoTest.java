package com.example.digital_gold.repository;

import com.example.digital_gold.domain.Asset;
import com.example.digital_gold.domain.AssetPrice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

/**
* @author Fiona Gray
* */

@SpringBootTest
@ActiveProfiles("test")
class JdbcAssetPriceDaoTest {

    private AssetPriceDao assetPriceDaoTest;

    @Autowired
    public JdbcAssetPriceDaoTest(AssetPriceDao assetPriceDaoTest) {
        this.assetPriceDaoTest = assetPriceDaoTest;
    }

    @Test
    public void assetPriceDaoNotNull() {
        assertThat(assetPriceDaoTest).isNotNull();
    }

    @Test
    void saveAssetPrice() {
        Asset testAsset = new Asset("RIC","RichCoin", "get rich with RichCoin");
        AssetPrice testAssetPrice = new AssetPrice(testAsset, 200.15, LocalDate.now());
        AssetPrice actualAssetPrice = assetPriceDaoTest.saveAssetPrice(testAssetPrice);
        assertThat(actualAssetPrice).isEqualTo(testAssetPrice);
    }

    @Test
    void findPriceByAssetCode() {
        AssetPrice expected = new AssetPrice(null, 3965.20, LocalDate.parse("2021-09-03"));
        AssetPrice actualEthereumPrice = assetPriceDaoTest.findPriceByAssetCode("ETH");
        assertThat(actualEthereumPrice).isEqualTo(expected);
    }
}