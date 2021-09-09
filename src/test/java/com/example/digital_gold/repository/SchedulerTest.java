package com.example.digital_gold.repository;

import com.example.digital_gold.domain.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

/*
@Author Jany Gaal
*/

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SchedulerTest {

    private RootRepository mockRepo;
    private Scheduler scheduler;

    @BeforeAll
    public void SetUp() {
        mockRepo = Mockito.mock(RootRepository.class);
        scheduler = new Scheduler(mockRepo);

        Asset testAsset001 = new Asset("DOGE", "Dogecoin", "Beschrijving");
        Asset testAsset002 = new Asset("ETH", "Ethereum", "Beschrijving");

        AssetPrice assetPrice001 = new AssetPrice(testAsset001, 1.00, LocalDate.now());
        AssetPrice assetPrice002 = new AssetPrice(testAsset002, 1.00, LocalDate.now());

        Mockito.when(mockRepo.findPriceByAssetCodeAndDate("DOGE", LocalDate.now())).thenReturn(assetPrice001);
        Mockito.when(mockRepo.findPriceByAssetCodeAndDate("ETH", LocalDate.now())).thenReturn(assetPrice002);
    }

    @AfterAll
    public void tearDown() {
        mockRepo = null;
        scheduler = null;
    }

    @Test
    void testTask() {
    }

    @Test
    void saveDailyPortfolioValues() {
    }

    @Test
    void calculateDailyValue() {
        Customer testCustomer300 = new Customer("TestUser105", "TestPassword",
                new FullName("Tester", "van", "Tester"),
                new Address(1, "TestStraat", "1111AA", "TestCity"),
                new CustomerDetails(Date.valueOf("1900-01-01"), "753654852", "tester@gmail.com", "Nl123456789"));
        Asset testAsset001 = new Asset("DOGE", "Dogecoin", "Beschrijving");
        Asset testAsset002 = new Asset("ETH", "Ethereum", "Beschrijving");
        HashMap<Asset, Double> testAssetsMap = new HashMap();
        testAssetsMap.put(testAsset001, 7566.00);
        testAssetsMap.put(testAsset002, 5000.00);
        Portfolio portfolio = new Portfolio(testCustomer300, testAssetsMap);
        double expected = 12566.00;
        double actual = scheduler.calculateDailyValue(portfolio);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void calculateDailyValueNoAssets() {
        Customer testCustomer300 = new Customer("TestUser105", "TestPassword",
                new FullName("Tester", "van", "Tester"),
                new Address(1, "TestStraat", "1111AA", "TestCity"),
                new CustomerDetails(Date.valueOf("1900-01-01"),"753654852","tester@gmail.com", "Nl123456789"));
        Asset testAsset001 = new Asset("DOGE", "Dogecoin", "Beschrijving");
        Asset testAsset002 = new Asset("ETH", "Ethereum", "Beschrijving");
        HashMap<Asset, Double> testAssetsMap = new HashMap();
        testAssetsMap.put(testAsset001, 0.00);
        testAssetsMap.put(testAsset002, 0.00);
        Portfolio portfolio = new Portfolio(testCustomer300, testAssetsMap);
        double expected = 0.00;
        double actual = scheduler.calculateDailyValue(portfolio);
        assertThat(actual).isEqualTo(expected);
    }

    // todo tests for  daily asset prices
}