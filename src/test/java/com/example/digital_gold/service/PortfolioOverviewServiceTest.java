package com.example.digital_gold.service;

import com.example.digital_gold.repository.RootRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PortfolioOverviewServiceTest {

    private PortfolioOverviewService portfolioOverviewService;
    private RootRepository mockRepo;

    @BeforeAll
    public void setUp() {
        mockRepo = Mockito.mock(RootRepository.class);
        portfolioOverviewService = new PortfolioOverviewService(mockRepo);
    }

    @AfterAll
    public void tearDown() {
        mockRepo = null;
        portfolioOverviewService = null;
    }

/*    @Test
    void getPortfolioOverviewToday() {
        PortfolioValueOverview = new PortfolioValueOverview()
    }*/
}