package com.example.digital_gold.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
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

    private final PortfolioDao portfolioDaoTest;

    @Autowired
    public JdbcPortfolioDaoTest(PortfolioDao portfolioDaoTest) {
        this.portfolioDaoTest = portfolioDaoTest;
    }

    @Test
    public void portfolioDAONotNull() {
        assertThat(portfolioDaoTest).isNotNull();
    }

    @Test
    public void addPortfolioAssetTest() {
        PortfolioDatabase testAsset = new PortfolioDatabase
                ("TestUser100", "BTC", 5);
        int expected = 1;
        int actual = portfolioDaoTest.addPortfolioAsset(testAsset);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void addPortfolioAssetTestDuplicateUser() {
        PortfolioDatabase testAsset = new PortfolioDatabase
                ("TestUser101", "BTC", 5);
        try {
            portfolioDaoTest.addPortfolioAsset(testAsset);
        } catch (DuplicateKeyException e) {
            System.out.println("SQL test error addPortfolioAssetTestDuplicateUser geslaagd");
        }
    }

    //TODO zorgen dat er geen onbekende Asset de database in gaat ?
/*    @Test
    public void addPortfolioAssetTestUnknownAssetCode() {
        PortfolioDatabase testAsset = new PortfolioDatabase
                ("TestUser101", "AAA", 6);
        int expected = 0;
        int actual = portfolioDaoTest.addPortfolioAsset(testAsset);
        assertThat(actual).isEqualTo(expected);
    }*/

    @Test
    public void updatePortfolioAsset() {
        PortfolioDatabase portfolioDatabase = new PortfolioDatabase
                ("TestUser100", "BTC", 75);
        int expected = 1;
        int actual = portfolioDaoTest.updatePortfolioAsset(portfolioDatabase);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void updatePortfolioAssetUnknownUser() {
        PortfolioDatabase portfolioDatabase = new PortfolioDatabase
                ("TestUser150", "BTC", 25);
        int expected = 0;
        int actual = portfolioDaoTest.updatePortfolioAsset(portfolioDatabase);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void updatePortfolioAssetUnknownAssetCode() {
            PortfolioDatabase portfolioDatabase = new PortfolioDatabase
                    ("TestUser100", "AAA", 75);
            int expected = 0;
            int actual = portfolioDaoTest.updatePortfolioAsset(portfolioDatabase);
            assertThat(actual).isEqualTo(expected);
        }

    @Test
    public void deletePortfolioAssetInDB() {
        PortfolioDatabase testDelete = new PortfolioDatabase
                ("TestUser102", "ETH", 25);
        int expected = 1;
        int actual = portfolioDaoTest.deletePortfolioAsset(testDelete);
        List<PortfolioDatabase> expected2 = new ArrayList<>();
        List<PortfolioDatabase> actual2 = portfolioDaoTest.getPortfolioAssetsByUsername("TestUser102");
        assertThat(actual).isEqualTo(expected);
        assertThat(actual2).isEqualTo(expected2);
    }

    @Test
    public void deletePortfolioAssetNotInDB() {
        int expected = 0;
        PortfolioDatabase testDelete = new PortfolioDatabase
                ("TestUser025", "AAA", 25);
        int actual = portfolioDaoTest.deletePortfolioAsset(testDelete);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void getPortfolioAssetByUsername1Asset () {
        List<PortfolioDatabase> expected = new ArrayList<>();
        expected.add(new PortfolioDatabase
                ("TestUser104", "BTC", 3.00));
        List<PortfolioDatabase> actual = portfolioDaoTest.getPortfolioAssetsByUsername("TestUser104");
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void getPortfolioAssetsByUsernameMultipleAssets () {
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

    @Test
    public void getPortfolioAssetByUsernameUnknownUser () {
        List<PortfolioDatabase> expected = new ArrayList<>();
        List<PortfolioDatabase> actual = portfolioDaoTest.getPortfolioAssetsByUsername("TestUser150");
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void getAllUsersWithAPortfolio() {
        List<String> expected = new ArrayList<>();
        expected.add("TestUser100");
        expected.add("TestUser101");
        expected.add("TestUser104");
        expected.add("TestUser105");
        List<String> actual = portfolioDaoTest.getAllUsersWithAPortfolio();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void getPortfolioAssetByUsernameAssetCode() {
        double expected = 5000.00;
        double actual = portfolioDaoTest.getPortfolioAssetByUsernameAssetCode("TestUser105", "ETH" );
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void getPortfolioAssetByUsernameAssetCodeUnknownUser() {
        try {
            portfolioDaoTest.getPortfolioAssetByUsernameAssetCode("TestUser150", "ETH" );
        } catch (EmptyResultDataAccessException e) {
            System.out.println("SQL error test getPortfolioAssetByUsernameAssetCodeUnknownUser geslaagd");
        }
    }
}