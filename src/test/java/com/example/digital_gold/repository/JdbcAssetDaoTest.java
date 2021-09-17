package com.example.digital_gold.repository;

import com.example.digital_gold.domain.Asset;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import java.util.ArrayList;
import java.util.List;

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
    public void findAssetByAssetCode() {
        Asset expected = new Asset("BTC", "Bitcoin", "Beschrijving");
        Asset actual = assetDaoTest.findAssetByAssetCode("BTC");
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void findAllAssets() {
        List<Asset> expectedAssetList = new ArrayList<>();
        Asset btc = new Asset("BTC", "Bitcoin", "Beschrijving");
        Asset eth = new Asset("ETH", "Ethereum", "Beschrijving");
        Asset ada = new Asset("ADA", "Cardano", "Beschrijving");
        Asset bnb = new Asset("BNB", "Binance Coin", "Beschrijving");
        Asset usdt = new Asset("USDT", "Tether", "Beschrijving");
        Asset xrp = new Asset("XRP", "XRP", "Beschrijving");
        Asset doge = new Asset("DOGE", "Dogecoin", "Beschrijving");
        Asset sol = new Asset("SOL", "Solana", "Beschrijving");
        Asset dot = new Asset("DOT", "Poldakot", "Beschrijving");
        Asset usdc = new Asset("USDC", "USD Coin", "Beschrijving");
        Asset uni = new Asset("UNI", "Uniswap", "Beschrijving");
        Asset link = new Asset("LINK", "Chainlink", "Beschrijving");
        Asset luna = new Asset("LUNA", "Terra", "Beschrijving");
        Asset bch = new Asset("BCH", "Bitcoin Cash", "Beschrijving");
        Asset busd = new Asset("BUSD", "Binance USD", "Beschrijving");
        Asset ltc = new Asset("LTC", "Litecoin", "Beschrijving");
        Asset icp = new Asset("ICP", "Internet Computer", "Beschrijving");
        Asset wbtc = new Asset("WBTC", "Wrapped Bitcoin", "Beschrijving");
        Asset matic = new Asset("MATIC", "Polygon", "Beschrijving");
        Asset vet = new Asset("VET", "VeChain", "Beschrijving");
        expectedAssetList.add(btc);
        expectedAssetList.add(eth);
        expectedAssetList.add(ada);
        expectedAssetList.add(bnb);
        expectedAssetList.add(usdt);
        expectedAssetList.add(xrp);
        expectedAssetList.add(doge);
        expectedAssetList.add(sol);
        expectedAssetList.add(dot);
        expectedAssetList.add(usdc);
        expectedAssetList.add(uni);
        expectedAssetList.add(link);
        expectedAssetList.add(luna);
        expectedAssetList.add(bch);
        expectedAssetList.add(busd);
        expectedAssetList.add(ltc);
        expectedAssetList.add(icp);
        expectedAssetList.add(wbtc);
        expectedAssetList.add(matic);
        expectedAssetList.add(vet);
        List<Asset> actualAssetList = assetDaoTest.findAllAssets();
        assertThat(actualAssetList).isEqualTo(expectedAssetList);
    }
}