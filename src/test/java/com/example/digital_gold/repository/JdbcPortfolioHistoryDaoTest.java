package com.example.digital_gold.repository;

import com.example.digital_gold.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

    /*@Test
    void savePortfolioValue() {
        Customer testCustomer = new Customer("TestUser200", "TestPassword", "zoutje",
                new FullName("Tester", "van", "Tester"),
                new Address(1, "TestStraat", "1111AA", "TestCity"),
                new CustomerDetails(Date.valueOf("1900-01-01"),"753654852","tester@gmail.com","Nl181234567890"));
        PortfolioHistory testPortfolioHistory = new PortfolioHistory(testCustomer, LocalDate.now(),254753.53);
        int expected = 1;
        int actual = porfolioHistoryDaoTest.savePortfolioValue(testPortfolioHistory);
        assertThat(actual).isEqualTo(expected);
    }*/

    @Test
    void getPortfolioValueByUserName() {
        PortfolioHistory testPortfolio = new PortfolioHistory(null, LocalDate.parse("2021-09-04"), 254159.50);
        List<PortfolioHistory> expected = new ArrayList<>();
        expected.add(testPortfolio);
        List<PortfolioHistory> actual = porfolioHistoryDaoTest.getPortfolioValuesByUserName("TestUser201");
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
        List<PortfolioHistory> actual = porfolioHistoryDaoTest.getPortfolioValuesByUserName("TestUser202");
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getPortfolioValueByUserNameNull() {
        List<PortfolioHistory> expected = new ArrayList<>();
        List<PortfolioHistory> actual = porfolioHistoryDaoTest.getPortfolioValuesByUserName("TestUser203");
        assertThat(actual).isEqualTo(expected);
    }
}