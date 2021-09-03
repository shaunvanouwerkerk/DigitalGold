package com.example.digital_gold.repository;

import com.example.digital_gold.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Date;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class RootRepositoryTest {

    private RootRepository rootRepository;


    @Autowired
    public RootRepositoryTest(RootRepository rootRepository) {
        this.rootRepository = rootRepository;
    }

    @Test
    void savePortfolio() {
        FullName testfullname = new FullName("PortfolioTester", "", "PortfolioTester");
        Address testadress = new Address(1, "TestStraat", "1111AA", "TestCity");
        CustomerDetails testcustomerDetails = new CustomerDetails(Date.valueOf("1900-01-01"), "753654852",
                "PortfolioTester@gmail.com");
        Customer testUser005 = new Customer("TestUserTest35", "TestPassword", "zoutje", testfullname,
                testadress, testcustomerDetails);

        Asset testAsset01 = new Asset("DGC");
        Asset testAsset02 = new Asset("ETH");

        HashMap<Asset, Double> testAssetsMap = new HashMap();

        testAssetsMap.put(testAsset01, 525.25);
        testAssetsMap.put(testAsset02, 7512.00);

        Portfolio expected = new Portfolio(testUser005, testAssetsMap);
        Portfolio actual = rootRepository.savePortfolio(expected);
        assertThat(actual).isEqualTo(expected);
    }

        @Test
        void updatePortfolio() {
            FullName testfullname = new FullName("PortfolioTester", "", "PortfolioTester");
            Address testadress = new Address(1, "TestStraat", "1111AA", "TestCity");
            CustomerDetails testcustomerDetails = new CustomerDetails(Date.valueOf("1900-01-01"),"753654852",
                    "PortfolioTester@gmail.com" );
            Customer testUser005 = new Customer("TestUserTest35", "TestPassword", "zoutje", testfullname,
                    testadress, testcustomerDetails);

            Asset testAsset01 = new Asset("DGC");
            Asset testAsset02 = new Asset("ETH");

            HashMap<Asset, Double> testAssetsMap = new HashMap();

            testAssetsMap.put(testAsset01, 1.00);
            testAssetsMap.put(testAsset02, 1.00);

            Portfolio expected = new Portfolio(testUser005, testAssetsMap);
            Portfolio actual = rootRepository.updatePortfolio(expected);
            assertThat(actual).isEqualTo(expected);






    }
}