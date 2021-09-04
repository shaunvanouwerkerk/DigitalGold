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
        JdbcPortfolioDao.PortfolioDatabase testAsset = new JdbcPortfolioDao.PortfolioDatabase
                ("TestUser100", "BTC", 5);
        int expected = 1;
        int actual = portfolioDaoTest.addPortfolioAsset(testAsset);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void updatePortfolioAsset() {
        JdbcPortfolioDao.PortfolioDatabase expected = new JdbcPortfolioDao.PortfolioDatabase
                ("TestUser101", "BTC", 25);
        JdbcPortfolioDao.PortfolioDatabase actual = portfolioDaoTest.updatePortfolioAsset(expected);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void deletePortfolioAssetInDB() {
        JdbcPortfolioDao.PortfolioDatabase testDelete = new JdbcPortfolioDao.PortfolioDatabase
                ("TestUser102", "ETH", 25);
        int expected = 1;
        int actual = portfolioDaoTest.deletePortfolioAsset(testDelete);
    }

    @Test
    public void deletePortfolioAssetNotInDB() {
        int expected = 0;
        JdbcPortfolioDao.PortfolioDatabase testDelete = new JdbcPortfolioDao.PortfolioDatabase
                ("TestUser003", "ETH", 25);
        int actual = portfolioDaoTest.deletePortfolioAsset(testDelete);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void getPortfolioAssetByUsername () {
        List<JdbcPortfolioDao.PortfolioDatabase> expected = new ArrayList<>();
        expected.add(new JdbcPortfolioDao.PortfolioDatabase
                ("TestUser104", "BTC", 3.00));
        List<JdbcPortfolioDao.PortfolioDatabase> actual = portfolioDaoTest.getPortfolioAssetsByUsername("TestUser104");
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void getPortfolioAssetsByUsername () {
        JdbcPortfolioDao.PortfolioDatabase testPortfolioDatabase1 =new JdbcPortfolioDao.PortfolioDatabase
                ("TestUser105", "DOGE", 7566.00);
        JdbcPortfolioDao.PortfolioDatabase testPortfolioDatabase2 =new JdbcPortfolioDao.PortfolioDatabase
                ("TestUser105", "ETH", 5000.00);
        List<JdbcPortfolioDao.PortfolioDatabase> expected = new ArrayList<>();
        expected.add(testPortfolioDatabase1);
        expected.add(testPortfolioDatabase2);
        List<JdbcPortfolioDao.PortfolioDatabase> actual = portfolioDaoTest.getPortfolioAssetsByUsername("TestUser105");
        assertThat(actual).isEqualTo(expected);
    }
}