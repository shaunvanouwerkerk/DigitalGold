package com.example.digital_gold.repository;

import com.example.digital_gold.domain.Asset;
import com.example.digital_gold.domain.AssetPrice;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void saveAssetPrice() {
        Asset testAsset = new Asset("RIC","RichCoin", "get rich with RichCoin");
        AssetPrice testAssetPrice = new AssetPrice(testAsset, 200.15, LocalDate.now());
        AssetPrice actualAssetPrice = assetPriceDaoTest.saveAssetPrice(testAssetPrice);
        assertThat(actualAssetPrice).isEqualTo(testAssetPrice);
    }

    @Test
    public void findPriceByAssetCodeAndDate() {
        AssetPrice expected = new AssetPrice(null, 3965.20, LocalDate.parse("2021-09-03"));
        AssetPrice actual = assetPriceDaoTest.findPriceByAssetCodeAndDate("ETH", LocalDate.parse("2021-09-03"));
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void findPricesByAssetCode() {
        AssetPrice ethereum1 = new AssetPrice(null, 3965.20, LocalDate.parse("2021-09-03"));
        AssetPrice ethereum2 = new AssetPrice(null, 3406.78, LocalDate.parse("2021-09-04"));
        AssetPrice ethereum3 = new AssetPrice(null, 12.00, LocalDate.parse("2021-09-05"));
        List<AssetPrice> expectedEthereumOverview = new ArrayList<>();
        expectedEthereumOverview.add(ethereum1);
        expectedEthereumOverview.add(ethereum2);
        expectedEthereumOverview.add(ethereum3);
        List<AssetPrice> actualEthereumPrice = assetPriceDaoTest.findPricesByAssetCode("ETH");
        assertThat(actualEthereumPrice).isEqualTo(expectedEthereumOverview);
    }

    // todo: caseinsensitive map gebruiken?
    @Test
    public void findAllAvailableAssetsByDate() {
        List<Map<String, Object>> expected = new ArrayList<>();
        Map<String, Object> testAssetOverview1 = new HashMap<>();
        Map<String, Object> testAssetOverview2 = new HashMap<>();
        testAssetOverview1.put("ASSETCODE", "ETH");
        testAssetOverview1.put("PRICE", 3406.78);
        testAssetOverview2.put("ASSETCODE", "BTC");
        testAssetOverview2.put("PRICE", 42273.5);
        expected.add(testAssetOverview1);
        expected.add(testAssetOverview2);
        List<Map<String, Object>> actual = assetPriceDaoTest.findAllAvailableAssets(LocalDate.parse("2021-09-04"));
        assertThat(actual).isEqualTo(expected);
    }


}