package com.example.digital_gold.repository;

import com.example.digital_gold.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class RootRepositoryTest {

    private final RootRepository rootRepository;


    @Autowired
    public RootRepositoryTest(RootRepository rootRepository) {
        this.rootRepository = rootRepository;
    }

    public Customer setUpCustomer1() {
        return new Customer("TestUser108", "TestPassword", "testzoutje", true,
                new FullName("Tester", "van", "Tester"),
                new Address(1, "TestStraat", "1111AA", "TestCity"),
                new CustomerDetails(Date.valueOf("1900-01-01"), "753654852", "tester108@gmail.com", "NL10DIGO9876543215"));
    }

    public Customer setUpCustomer2() {
        return new Customer("TestUser106", "TestPassword", "testzoutje", true,
                new FullName("Tester", "van", "Tester"),
                new Address(1, "TestStraat", "1111AA", "TestCity"),
                new CustomerDetails(Date.valueOf("1900-01-01"), "753654852", "tester106@gmail.com", "NL10DIGO9876543215"));
    }

    public Customer setUpCustomer3() {
        return new Customer("TestUser107", "TestPassword", "testzoutje", true,
                new FullName("Tester", "van", "Tester"),
                new Address(1, "TestStraat", "1111AA", "TestCity"),
                new CustomerDetails(Date.valueOf("1900-01-01"), "753654852", "tester107@gmail.com", "NL10DIGO9876543215"));
    }

    public Customer setUpCustomer4() {
        return new Customer("TestUser109", "TestPassword", "testzoutje", true,
                new FullName("Tester", "van", "Tester"),
                new Address(1, "TestStraat", "1111AA", "TestCity"),
                new CustomerDetails(Date.valueOf("1900-01-01"), "753654852", "tester109@gmail.com", "NL10DIGO9876543215"));
    }

    public Map<Asset, Double> setUpAsset1() {
        Asset testasset = new Asset("DOGE", "Dogecoin", "Beschrijving");
        Map<Asset, Double> testmap = new HashMap<>();
        testmap.put(testasset, 0.00);
        return testmap;
    }

    public Map<Asset, Double> setUpAsset2() {
        Asset testasset = new Asset("DOT", "Poldakot", "Beschrijving");
        Map<Asset, Double> testmap = new HashMap<>();
        testmap.put(testasset, 36.47);
        return testmap;
    }

    public Map<Asset, Double> setUpAsset3() {
        Asset testasset = new Asset("ETH", "Ethereum", "Beschrijving");
        Map<Asset, Double> testmap = new HashMap<>();
        testmap.put(testasset, 55.15);
        return testmap;
    }

    public Map<Asset, Double> setUpAsset4() {
        Asset testasset1 = new Asset("BTC", "Bitcoin", "Beschrijving");
        Asset testasset2 = new Asset("ETH", "Ethereum", "Beschrijving");
        Asset testasset3 = new Asset("BUSD", "Binance USD", "Beschrijving");
        Asset testasset4 = new Asset("SOL", "Solana", "Beschrijving");
        Asset testasset5 = new Asset("VET", "VeChain", "Beschrijving");
        Map<Asset, Double> testmap = new HashMap<>();
        testmap.put(testasset1, 3.00);
        testmap.put(testasset2, 25.15);
        testmap.put(testasset3, 758.38);
        testmap.put(testasset4, 355.15);
        testmap.put(testasset5, 0.00);
        return testmap;
    }

    public Map<Asset, Double> setUpAsset5() {
        Asset testasset1 = new Asset("BTC", "Bitcoin", "Beschrijving");
        Asset testasset2 = new Asset("ETH", "Ethereum", "Beschrijving");
        Asset testasset3 = new Asset("BUSD", "Binance USD", "Beschrijving");
        Asset testasset4 = new Asset("SOL", "Solana", "Beschrijving");
        Map<Asset, Double> testmap = new HashMap<>();
        testmap.put(testasset1, 3.00);
        testmap.put(testasset2, 25.15);
        testmap.put(testasset3, 758.38);
        testmap.put(testasset4, 355.15);
        return testmap;
    }

    @Test
    void portfolioToDatabaseDeleteTest() {
        Portfolio testPortfolio = new Portfolio(setUpCustomer1(),setUpAsset1());
        try {
            rootRepository.saveAssetChangesInPortfolio(testPortfolio);
        }catch (EmptyResultDataAccessException e) {
            System.out.println("SQL error test portfolioToDatabaseDeleteTest geslaagd");
        }
    }

    @Test
    void portfolioToDatabaseSaveTest() {
        Portfolio expected = new Portfolio(setUpCustomer2(),setUpAsset2());
        rootRepository.saveAssetChangesInPortfolio(expected);
        Portfolio actual = rootRepository.getPortfolioForCustomer(setUpCustomer2().getUsername());
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void portfolioToDatabaseUpdateTest() {
        Portfolio expected = new Portfolio(setUpCustomer3(),setUpAsset3());
        rootRepository.saveAssetChangesInPortfolio(expected);
        Portfolio actual = rootRepository.getPortfolioForCustomer(setUpCustomer3().getUsername());
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void portfolioToDatabaseAlloptionsTest() {
        Portfolio expected = new Portfolio(setUpCustomer4(),setUpAsset5());
        Portfolio input = new Portfolio(setUpCustomer4(), setUpAsset4());
        rootRepository.saveAssetChangesInPortfolio(input);
        Portfolio actual = rootRepository.getPortfolioForCustomer(setUpCustomer4().getUsername());
        assertThat(actual).isEqualTo(expected);
    }


    @Test
    void getPortfolioAssetByUsernameAssetCode () {
        double expected = 5000.00;
        double actual = rootRepository.getPortfolioAssetByUsernameAssetCode("TestUser105", "ETH");
        assertThat(actual).isEqualTo(expected);
    }
}

