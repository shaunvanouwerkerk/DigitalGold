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
        Customer TestUser100 = new Customer("TestUser100", "TestPassword", "testzoutje", true,
                new FullName("JanTester", "van", "JanssenTester"),
                new Address(1, "TestStraat", "1111AA", "TestCity"),
                new CustomerDetails(Date.valueOf("1950-01-01"), "753654852", "tester100@gmail.com", "NL10DIGO9876543210"));

        Asset testasset = new Asset("DOGE", "Dogecoin", "Beschrijving");
        Map<Asset, Double> testmap01 = new HashMap<>();
        testmap01.put(testasset, 0.00);

        Portfolio testPortfolio = new Portfolio(TestUser100,testmap01);
        try {
            rootRepository.saveAssetChangesInPortfolio(testPortfolio);
        }catch (EmptyResultDataAccessException e) {
            System.out.println("SQL error test portfolioToDatabaseDeleteTest geslaagd");
        }
    }

    @Test
    void portfolioToDatabaseSaveTest() {
        Customer TestUser101 = new Customer("TestUser101", "TestPassword", "testzoutje", true,
                new FullName("JanTester", "van", "JanssenTester"),
                new Address(1, "TestStraat", "1111AA", "TestCity"),
                new CustomerDetails(Date.valueOf("1950-01-01"), "753654851", "tester101@gmail.com", "NL10DIGO9876543211"));
        Asset testasset1011 = new Asset("MATIC", "Polygon", "Beschrijving");
        Asset testasset1012 = new Asset("BTC", "Bitcoin", "Beschrijving");
        Map<Asset, Double> testmap101 = new HashMap<>();
        testmap101.put(testasset1011, 1.00);
        testmap101.put(testasset1012, 1.00);
        Portfolio expected = new Portfolio(TestUser101,testmap101);
        rootRepository.saveAssetChangesInPortfolio(expected);
        Portfolio actual = rootRepository.getPortfolioForCustomer("TestUser101");
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void portfolioToDatabaseUpdateTest() {
        Customer TestUser102 = new Customer("TestUser102", "TestPassword", "testzoutje", true,
                new FullName("JanTester", "van", "JanssenTester"),
                new Address(1, "TestStraat", "1111AA", "TestCity"),
                new CustomerDetails(Date.valueOf("1950-01-01"), "753654852", "tester102@gmail.com", "NL10DIGO9876543212"));
        Asset testasset1021 = new Asset("MATIC", "Polygon", "Beschrijving");
        Map<Asset, Double> testmap102 = new HashMap<>();
        testmap102.put(testasset1021, 5.00);
        Portfolio expected = new Portfolio(TestUser102, testmap102);
        rootRepository.saveAssetChangesInPortfolio(expected);
        Portfolio actual = rootRepository.getPortfolioForCustomer("TestUser102");
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void portfolioToDatabaseAlloptionsTest() {
        Customer TestUser103 = new Customer("TestUser103", "TestPassword", "testzoutje", true,
                new FullName("JanTester", "van", "JanssenTester"),
                new Address(1, "TestStraat", "1111AA", "TestCity"),
                new CustomerDetails(Date.valueOf("1950-01-01"), "753654853", "tester103@gmail.com", "NL10DIGO9876543213"));
        Asset testasset1031 = new Asset("MATIC", "Polygon", "Beschrijving");
        Asset testasset1032 = new Asset("ETH", "Ethereum", "Beschrijving");
        Asset testasset1033 = new Asset("BTC", "Bitcoin", "Beschrijving");
        Asset testasset1034 = new Asset("BUSD", "Binance USD", "Beschrijving");
        Map<Asset, Double> testmap1031 = new HashMap<>();
        testmap1031.put(testasset1031, 0.00);
        testmap1031.put(testasset1032, 3.00);
        testmap1031.put(testasset1033, 157.33);
        testmap1031.put(testasset1034, 1.00);

        Map<Asset, Double> testmap1032= new HashMap<>();
        testmap1032.put(testasset1032, 3.00);
        testmap1032.put(testasset1033, 157.33);
        testmap1032.put(testasset1034, 1.00);

        Portfolio expected = new Portfolio(TestUser103,testmap1032);
        Portfolio input = new Portfolio(TestUser103, testmap1031);
        rootRepository.saveAssetChangesInPortfolio(input);
        Portfolio actual = rootRepository.getPortfolioForCustomer("TestUser103");
        assertThat(actual).isEqualTo(expected);
    }


    @Test
    void getPortfolioAssetByUsernameAssetCode () {
        double expected = 1.00;
        double actual = rootRepository.getPortfolioAssetByUsernameAssetCode("TestUser104", "MATIC");
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getPortfolioForCustomer() {
        Customer TestUser176 = new Customer("TestUser176", "TestPassword", "testzoutje", true,
                new FullName("JanTester", "van", "JanssenTester"),
                new Address(1, "TestStraat", "1111AA", "TestCity"),
                new CustomerDetails(Date.valueOf("1950-01-01"), "17565486", "tester176@gmail.com", "NL10DIGO9876543166"));
        Asset testasset1761= new Asset("LUNA", "Terra", "Beschrijving");
        Asset testasset1762 = new Asset("SOL", "Solana", "Beschrijving");
        Map<Asset, Double> testmap101 = new HashMap<>();
        testmap101.put(testasset1761, 1.00);
        testmap101.put(testasset1762, 1.00);
        Portfolio expected = new Portfolio(TestUser176,testmap101);
        Portfolio actual = rootRepository.getPortfolioForCustomer("TestUser176");
        assertThat(actual).isEqualTo(expected);
    }
}

