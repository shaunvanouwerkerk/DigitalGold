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


    @Test
    void portfolioToDatabaseDeleteTest() {
        Customer testCustomer01 = new Customer("TestUser108", "TestPassword", "testzoutje", true,
                new FullName("Tester", "van", "Tester"),
                new Address(1, "TestStraat", "1111AA", "TestCity"),
                new CustomerDetails(Date.valueOf("1900-01-01"), "753654852", "tester108@gmail.com", "NL10DIGO9876543215"));

        Asset testasset = new Asset("DOGE", "Dogecoin", "Beschrijving");
        Map<Asset, Double> testmap01 = new HashMap<>();
        testmap01.put(testasset, 0.00);

        Portfolio testPortfolio = new Portfolio(testCustomer01,testmap01);
        try {
            rootRepository.saveAssetChangesInPortfolio(testPortfolio);
        }catch (EmptyResultDataAccessException e) {
            System.out.println("SQL error test portfolioToDatabaseDeleteTest geslaagd");
        }
    }

    @Test
    void portfolioToDatabaseSaveTest() {
        Customer testcustomer02 = new Customer("TestUser106", "TestPassword", "testzoutje", true,
                new FullName("Tester", "van", "Tester"),
                new Address(1, "TestStraat", "1111AA", "TestCity"),
                new CustomerDetails(Date.valueOf("1900-01-01"), "753654852", "tester106@gmail.com", "NL10DIGO9876543215"));
        Asset testasset = new Asset("DOT", "Poldakot", "Beschrijving");
        Map<Asset, Double> testmap02 = new HashMap<>();
        testmap02.put(testasset, 36.47);

        Portfolio expected = new Portfolio(testcustomer02,testmap02);
        rootRepository.saveAssetChangesInPortfolio(expected);
        Portfolio actual = rootRepository.getPortfolioForCustomer("TestUser106");
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void portfolioToDatabaseUpdateTest() {
        Customer testcustomer03 = new Customer("TestUser107", "TestPassword", "testzoutje", true,
                new FullName("Tester", "van", "Tester"),
                new Address(1, "TestStraat", "1111AA", "TestCity"),
                new CustomerDetails(Date.valueOf("1900-01-01"), "753654852", "tester107@gmail.com", "NL10DIGO9876543215"));
        Asset testasset = new Asset("ETH", "Ethereum", "Beschrijving");
        Map<Asset, Double> testmap03 = new HashMap<>();
        testmap03.put(testasset, 55.15);

        Portfolio expected = new Portfolio(testcustomer03,testmap03);
        rootRepository.saveAssetChangesInPortfolio(expected);
        Portfolio actual = rootRepository.getPortfolioForCustomer("TestUser107");
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void portfolioToDatabaseAlloptionsTest() {
        Customer testcustomer04 = new Customer("TestUser109", "TestPassword", "testzoutje", true,
                new FullName("Tester", "van", "Tester"),
                new Address(1, "TestStraat", "1111AA", "TestCity"),
                new CustomerDetails(Date.valueOf("1900-01-01"), "753654852", "tester109@gmail.com", "NL10DIGO9876543215"));

        Asset testasset1 = new Asset("BTC", "Bitcoin", "Beschrijving");
        Asset testasset2 = new Asset("ETH", "Ethereum", "Beschrijving");
        Asset testasset3 = new Asset("BUSD", "Binance USD", "Beschrijving");
        Asset testasset4 = new Asset("SOL", "Solana", "Beschrijving");
        Map<Asset, Double> testmap05= new HashMap<>();
        testmap05.put(testasset1, 3.00);
        testmap05.put(testasset2, 25.15);
        testmap05.put(testasset3, 758.38);
        testmap05.put(testasset4, 355.15);

        Asset testasset5 = new Asset("BTC", "Bitcoin", "Beschrijving");
        Asset testasset6 = new Asset("ETH", "Ethereum", "Beschrijving");
        Asset testasset7 = new Asset("BUSD", "Binance USD", "Beschrijving");
        Asset testasset8 = new Asset("SOL", "Solana", "Beschrijving");
        Asset testasset9 = new Asset("VET", "VeChain", "Beschrijving");
        Map<Asset, Double> testmap06 = new HashMap<>();
        testmap06.put(testasset5, 3.00);
        testmap06.put(testasset6, 25.15);
        testmap06.put(testasset7, 758.38);
        testmap06.put(testasset8, 355.15);
        testmap06.put(testasset9, 0.00);

        Portfolio expected = new Portfolio(testcustomer04,testmap05);
        Portfolio input = new Portfolio(testcustomer04, testmap06);
        rootRepository.saveAssetChangesInPortfolio(input);
        Portfolio actual = rootRepository.getPortfolioForCustomer("TestUser109");
        assertThat(actual).isEqualTo(expected);
    }


    @Test
    void getPortfolioAssetByUsernameAssetCode () {
        double expected = 5000.00;
        double actual = rootRepository.getPortfolioAssetByUsernameAssetCode("TestUser105", "ETH");
        assertThat(actual).isEqualTo(expected);
    }
}

