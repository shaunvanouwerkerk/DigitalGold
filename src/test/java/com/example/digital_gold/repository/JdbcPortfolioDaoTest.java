package com.example.digital_gold.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Collections;
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
                ("TestUser109", "BTC", 5);
        int expected = 1;
        int actual = portfolioDaoTest.addPortfolioAsset(testAsset);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void addPortfolioAssetTestDuplicateUser() {
        PortfolioDatabase testAsset = new PortfolioDatabase
                ("TestUser109", "BTC", 5);
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
                ("TestUser109", "BTC", 7531);
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
                ("TestUser109", "BTV", 25);
        int expected = 1;
        int actual = portfolioDaoTest.deletePortfolioAsset(testDelete);
        assertThat(actual).isEqualTo(expected);
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
                ("TestUser110", "MATIC", 1.00));
        List<PortfolioDatabase> actual = portfolioDaoTest.getPortfolioAssetsByUsername("TestUser110");
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void getPortfolioAssetsByUsernameMultipleAssets () {
        PortfolioDatabase testPortfolioDatabase1 =new PortfolioDatabase
                ("TestUser111", "BTC", 1.00);
        PortfolioDatabase testPortfolioDatabase2 =new PortfolioDatabase
                ("TestUser111", "ETH", 1.00);
        PortfolioDatabase testPortfolioDatabase3 =new PortfolioDatabase
                ("TestUser111", "MATIC", 1.00);
        PortfolioDatabase testPortfolioDatabase4 =new PortfolioDatabase
                ("TestUser111", "DOGE", 1.00);
        List<PortfolioDatabase> expected = new ArrayList<>();
        expected.add(testPortfolioDatabase1);
        expected.add(testPortfolioDatabase2);
        expected.add(testPortfolioDatabase3);
        expected.add(testPortfolioDatabase4);
        List<PortfolioDatabase> actual = portfolioDaoTest.getPortfolioAssetsByUsername("TestUser111");
        Collections.sort(expected);
        Collections.sort(actual);
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
        expected.add("TestUser102");
        expected.add("TestUser103");
        expected.add("TestUser104");
        expected.add("TestUser105");
        expected.add("TestUser106");
        expected.add("TestUser107");
        expected.add("TestUser108");
        expected.add("TestUser109");
        expected.add("TestUser110");
        expected.add("TestUser111");
        expected.add("TestUser175");
        expected.add("TestUser176");
        List<String> actual = portfolioDaoTest.getAllUsersWithAPortfolio();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void getPortfolioAssetByUsernameAssetCode() {
        double expected = 1.00;
        double actual = portfolioDaoTest.getPortfolioAssetByUsernameAssetCode("TestUser108", "MATIC" );
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