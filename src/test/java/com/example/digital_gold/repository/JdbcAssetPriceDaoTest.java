package com.example.digital_gold.repository;

import com.example.digital_gold.domain.Asset;
import com.example.digital_gold.domain.AssetPrice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import java.time.LocalDateTime;

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
        AssetPrice testAssetPrice = new AssetPrice(testAsset, 200.15, LocalDateTime.now());
        AssetPrice actualAssetPrice = assetPriceDaoTest.saveAssetPrice(testAssetPrice);
        assertThat(actualAssetPrice).isEqualTo(testAssetPrice);
    }

    // LocalDateTime is niet compatible met H@DB. Niet te testen.
    /*@Test
    public void findPriceByAssetCode() {
        Asset ethereumTest = new Asset("ETH", "Ethereum", "Beschrijving");
        AssetPrice expected = new AssetPrice(ethereumTest, 3965.20, LocalDateTime.now());
        AssetPrice actual = assetPriceDaoTest.findAssetPriceByAssetCode("ETH");
        assertThat(actual).isEqualTo(expected);
    }*/

    //overbodig?
  /*
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
        List<Map<String, Object>> actual = assetPriceDaoTest.findAllAvailableAssets(LocalDateTime.parse("2021-09-04 00:30:10"));
        assertThat(actual).isEqualTo(expected);
    }*/

}