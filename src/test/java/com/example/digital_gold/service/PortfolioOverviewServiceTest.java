package com.example.digital_gold.service;

import com.example.digital_gold.domain.*;
import com.example.digital_gold.repository.RootRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PortfolioOverviewServiceTest {

    private RootRepository mockRepo;
    private PortfolioOverviewService portfolioOverviewService;

    @BeforeAll
    public void Setup() {
        mockRepo = Mockito.mock(RootRepository.class);
        portfolioOverviewService = new PortfolioOverviewService(mockRepo);
        Customer testCustomer = new Customer("TestUser200", "TestPassword", "zoutje", true,
                new FullName("Tester", "van", "Tester"),
                new Address(1, "TestStraat", "1111AA", "TestCity"),
                new CustomerDetails(Date.valueOf("1950-01-01"),"753654852","tester@gmail.com","Nl181234567890"));
        List<PortfolioHistory> testList1 = new ArrayList<>();
        List<PortfolioHistory> testList2 = new ArrayList<>();
        testList1.add(new PortfolioHistory(testCustomer, LocalDate.now(), 5500.00));
        testList1.add(new PortfolioHistory(testCustomer, LocalDate.parse("2021-09-08"), 7545.75));
        testList1.add(new PortfolioHistory(testCustomer, LocalDate.parse("2021-09-07"), 157.75));
        Mockito.when(mockRepo.getPortfolioValuesForCustomer("TestUser200")).thenReturn(testList1);
        Mockito.when(mockRepo.getPortfolioValuesForCustomer("TestUser201")).thenReturn(testList2);
    }

    @AfterAll
    public void tearDown () {
        mockRepo = null;
        portfolioOverviewService = null;
    }

    @Test
    void getPortfolioOverviewToday() {
        PortfolioValueOverview expected = new PortfolioValueOverview(LocalDate.now(), 5500.00);
        PortfolioValueOverview actual = portfolioOverviewService.getPortfolioOverviewToday("TestUser200");
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getPortfolioOverviewTodayUnknowUser() {
        PortfolioValueOverview expected = null;
        PortfolioValueOverview actual = portfolioOverviewService.getPortfolioOverviewToday("TestUser201");
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getPortfolioOverview() {
        List<PortfolioValueOverview> expected = new ArrayList<>();
        PortfolioValueOverview p1 = new PortfolioValueOverview(LocalDate.now(),5500.00);
        PortfolioValueOverview p2 = new PortfolioValueOverview(LocalDate.parse("2021-09-08"),7545.75);
        PortfolioValueOverview p3 = new PortfolioValueOverview(LocalDate.parse("2021-09-07"),157.75);
        expected.add(p1);
        expected.add(p2);
        expected.add(p3);
        List<PortfolioValueOverview> actual = portfolioOverviewService.getPortfolioOverview("TestUser200");
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getPortfolioOverviewAssets() {
    }
}