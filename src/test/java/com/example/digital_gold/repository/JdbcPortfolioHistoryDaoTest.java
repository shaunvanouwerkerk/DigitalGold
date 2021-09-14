package com.example.digital_gold.repository;

import com.example.digital_gold.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/*
@Author Jany Gaal
*/

@SpringBootTest
@ActiveProfiles("test")
class JdbcPortfolioHistoryDaoTest {

    private final PortfolioHistoryDao porfolioHistoryDaoTest;

    @Autowired
    public JdbcPortfolioHistoryDaoTest(PortfolioHistoryDao porfolioHistoryDaoTest) {
        this.porfolioHistoryDaoTest = porfolioHistoryDaoTest;
    }

    @Test
    public void portfolioDAOHistoryNotNull() {
        assertThat(porfolioHistoryDaoTest).isNotNull();
    }

    @Test
    void savePortfolioValue() {
        Customer TestUser105 = new Customer("TestUser105", "TestPassword", "testzoutje", true,
                new FullName("JanTester", "van", "JanssenTester"),
                new Address(1, "TestStraat", "1111AA", "TestCity"),
                new CustomerDetails(Date.valueOf("1950-01-01"), "753654855", "tester105@gmail.com", "NL10DIGO9876543215"));
        PortfolioHistory testPortfolioHistory = new PortfolioHistory(TestUser105, LocalDate.now(),158.53);
        int expected = 1;
        int actual = porfolioHistoryDaoTest.savePortfolioValue(testPortfolioHistory);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void savePortfolioValueDuplicate() {
        Customer TestUser105 = new Customer("TestUser105", "TestPassword", "testzoutje", true,
                new FullName("JanTester", "van", "JanssenTester"),
                new Address(1, "TestStraat", "1111AA", "TestCity"),
                new CustomerDetails(Date.valueOf("1950-01-01"), "753654855", "tester105@gmail.com", "NL10DIGO9876543215"));
        PortfolioHistory testPortfolioHistory = new PortfolioHistory(TestUser105, LocalDate.now(),158.53);
        try {
            porfolioHistoryDaoTest.savePortfolioValue(testPortfolioHistory);
        } catch (DuplicateKeyException e) {
            System.out.println("SQL test error savePortfolioValueDuplicate geslaagd");
        }
    }

    @Test
    void getPortfolioValueByUserName() {
        PortfolioHistory testPortfolio = new PortfolioHistory(null, LocalDate.parse("2021-09-12"), 1.00);
        List<PortfolioHistory> expected = new ArrayList<>();
        expected.add(testPortfolio);
        List<PortfolioHistory> actual = porfolioHistoryDaoTest.getPortfolioValuesByUserName("TestUser106");
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getPortfolioValuesByUserName() {
        PortfolioHistory testPortfolio1 = new PortfolioHistory(null, LocalDate.parse("2021-09-04"), 254159.50);
        PortfolioHistory testPortfolio2 = new PortfolioHistory(null, LocalDate.parse("2021-09-03"), 250.00);
        PortfolioHistory testPortfolio3 = new PortfolioHistory(null, LocalDate.parse("2021-09-02"), 1.00);
        List<PortfolioHistory> expected = new ArrayList<>();
        expected.add(testPortfolio1);
        expected.add(testPortfolio2);
        expected.add(testPortfolio3);
        Collections.sort(expected);
        List<PortfolioHistory> actual = porfolioHistoryDaoTest.getPortfolioValuesByUserName("TestUser107");
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getPortfolioValueByUserNameNull() {
        List<PortfolioHistory> expected = new ArrayList<>();
        List<PortfolioHistory> actual = porfolioHistoryDaoTest.getPortfolioValuesByUserName("TestUser111");
        assertThat(actual).isEqualTo(expected);
    }
}