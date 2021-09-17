package com.example.digital_gold.repository;

import com.example.digital_gold.domain.AssetPrice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Fiona Gray
* */

// LocalDateTime en SQL query zijn niet compatible met H2 databse.
// Deze methodes moeten getest worden met MySQL DB, maar is wegens ()now niet continu te testen.
@SpringBootTest
public class MySqlJdbcAssetPriceDaoTest {

    private AssetPriceDao assetPriceDaoTest;

    @Autowired
    public MySqlJdbcAssetPriceDaoTest(AssetPriceDao assetPriceDaoTest) {
        this.assetPriceDaoTest = assetPriceDaoTest;
    }

    @Test
    public void assetPriceDaoNotNull() {
        assertThat(assetPriceDaoTest).isNotNull();
    }

    @Test
    void findAllAssetPrices() {
        List<AssetPrice> actual = assetPriceDaoTest.findAllAssetPrices();
        assertEquals(20, actual.size());
    }

// Door findPriceByAssetCode aan te passen is deze eenmalig toch te testen.
// stop in var. datetime en expected een datetime en price die je net opgeslagen hebt, om te kunnen testen
    /*@Test
    public void findPriceByAssetCode() {

        LocalDateTime datetime = dateTimeFormatter("2021-09-16 15:58:15");
        AssetPrice expected = new AssetPrice(null, 3964, datetime);
        AssetPrice actual = assetPriceDaoTest.findAssetPriceByAssetCode("ETH");
        assertThat(actual).isEqualTo(expected);
    }

    public LocalDateTime dateTimeFormatter (String resultSet) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String sqlDateTime = resultSet;
        LocalDateTime dateTime = LocalDateTime.parse(sqlDateTime, formatter);
        return dateTime;
    }*/
}
