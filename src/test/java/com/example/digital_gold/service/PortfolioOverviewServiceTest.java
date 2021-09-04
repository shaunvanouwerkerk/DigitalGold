package com.example.digital_gold.service;

import com.example.digital_gold.domain.*;
import com.example.digital_gold.repository.JdbcPortfolioDao;
import com.example.digital_gold.repository.RootRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

/*
@Author Jany Gaal
* */

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PortfolioOverviewServiceTest {

    private RootRepository mockRepo;
    private PortfolioOverviewService portfolioOverviewService;

    @BeforeAll
    public void SetUp() {
        mockRepo = Mockito.mock(RootRepository.class);
        portfolioOverviewService = new PortfolioOverviewService(mockRepo);

        Asset testAsset001 = new Asset("DOGE", "Dogecoin", "Beschrijving");
        Asset testAsset002 = new Asset("ETH", "Ethereum", "Beschrijving");

        AssetPrice assetPrice001 = new AssetPrice(testAsset001, 1.00, LocalDate.now());
        AssetPrice assetPrice002 = new AssetPrice(testAsset002, 1.00, LocalDate.now());

        Mockito.when(mockRepo.findPriceByAssetCode("DOGE")).thenReturn(assetPrice001);
        Mockito.when(mockRepo.findPriceByAssetCode("ETH")).thenReturn(assetPrice002);
    }

    @AfterAll
    public void teadDown() {
        mockRepo = null;
        portfolioOverviewService = null;
    }

    @Test
    void getOverviewAssets() {
    }

    @Test
    void getPortfolioForCustomer() {
    }


    @Test
    void calculateDailyValue() {
        Customer testCustomer300 = new Customer("TestUser105", "TestPassword", "zoutje",
                new FullName("Tester", "van", "Tester"),
                new Address(1, "TestStraat", "1111AA", "TestCity"),
                new CustomerDetails(Date.valueOf("1900-01-01"),"753654852","tester@gmail.com", "Nl123456789"));
        Asset testAsset001 = new Asset("DOGE", "Dogecoin", "Beschrijving");
        Asset testAsset002 = new Asset("ETH", "Ethereum", "Beschrijving");
        HashMap<Asset, Double> testAssetsMap = new HashMap();
        testAssetsMap.put(testAsset001, 7566.00);
        testAssetsMap.put(testAsset002, 5000.00);
        Portfolio portfolio = new Portfolio(testCustomer300, testAssetsMap);
        double expected = 12566.00;
        double actual = portfolioOverviewService.calculateDailyValue(portfolio);
        assertThat(actual).isEqualTo(expected);
    }
}