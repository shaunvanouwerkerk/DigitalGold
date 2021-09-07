package com.example.digital_gold.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/*
@Author Jany Gaal
*/

@SpringBootTest
@ActiveProfiles("test")
class JdbcPortfolioDaoTest {

    private PortfolioDao portfolioDaoTest;

    @Autowired
    public JdbcPortfolioDaoTest(PortfolioDao portfolioDaoTest) {
        this.portfolioDaoTest = portfolioDaoTest;
    }

    @Test
    public void portfolioDAONotNull() {
        assertThat(portfolioDaoTest).isNotNull();
    }

    @Test
    public void addPortfolioAssetTestRowsChanged() {
        PortfolioDatabase testAsset = new PortfolioDatabase
                ("TestUser100", "BTC", 5);
        int expected = 1;
        int actual = portfolioDaoTest.addPortfolioAsset(testAsset);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void updatePortfolioAsset() {
        PortfolioDatabase expected = new PortfolioDatabase
                ("TestUser101", "BTC", 25);
        PortfolioDatabase actual = portfolioDaoTest.updatePortfolioAsset(expected);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void deletePortfolioAssetInDB() {
        PortfolioDatabase testDelete = new PortfolioDatabase
                ("TestUser102", "ETH", 25);
        int expected = 1;
        int actual = portfolioDaoTest.deletePortfolioAsset(testDelete);
    }

    @Test
    public void deletePortfolioAssetNotInDB() {
        int expected = 0;
        PortfolioDatabase testDelete = new PortfolioDatabase
                ("TestUser003", "ETH", 25);
        int actual = portfolioDaoTest.deletePortfolioAsset(testDelete);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void getPortfolioAssetByUsername () {
        List<PortfolioDatabase> expected = new ArrayList<>();
        expected.add(new PortfolioDatabase
                ("TestUser104", "BTC", 3.00));
        List<PortfolioDatabase> actual = portfolioDaoTest.getPortfolioAssetsByUsername("TestUser104");
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void getPortfolioAssetsByUsername () {
        PortfolioDatabase testPortfolioDatabase1 =new PortfolioDatabase
                ("TestUser105", "DOGE", 7566.00);
        PortfolioDatabase testPortfolioDatabase2 =new PortfolioDatabase
                ("TestUser105", "ETH", 5000.00);
        List<PortfolioDatabase> expected = new ArrayList<>();
        expected.add(testPortfolioDatabase1);
        expected.add(testPortfolioDatabase2);
        List<PortfolioDatabase> actual = portfolioDaoTest.getPortfolioAssetsByUsername("TestUser105");
        assertThat(actual).isEqualTo(expected);
    }
}