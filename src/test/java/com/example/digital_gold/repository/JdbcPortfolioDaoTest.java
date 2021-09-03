package com.example.digital_gold.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

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
    public void addPortfolioAsset() {
        RootRepository.PortfolioDatabase expected = new RootRepository.PortfolioDatabase
                ("TestUser001", " BTC", 5);
        RootRepository.PortfolioDatabase actual = portfolioDaoTest.addPortfolioAsset(expected);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void updatePortfolioAsset() {
        RootRepository.PortfolioDatabase expected = new RootRepository.PortfolioDatabase
                ("TestUser001", " BTC", 25);
        RootRepository.PortfolioDatabase actual = portfolioDaoTest.updatePortfolioAsset(expected);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void deletePortfolioAsset() {
        RootRepository.PortfolioDatabase expected = null;
        RootRepository.PortfolioDatabase testDelete = new RootRepository.PortfolioDatabase
                ("TestUser001", " BTC", 25);
        RootRepository.PortfolioDatabase actual = portfolioDaoTest.deletePortfolioAsset(testDelete);
        assertThat(actual).isEqualTo(expected);
    }
}