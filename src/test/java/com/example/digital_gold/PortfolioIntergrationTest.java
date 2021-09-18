package com.example.digital_gold;

import com.example.digital_gold.controller.PortfolioOverviewController;
import com.example.digital_gold.repository.MapDatabase;
import com.example.digital_gold.service.PortfolioOverviewService;
import com.example.digital_gold.service.PortfolioValueOverview;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
//@ActiveProfiles("test")
public class PortfolioIntergrationTest {

    PortfolioOverviewController portfolioOverviewController;
    PortfolioOverviewService portfolioOverviewService;
    MapDatabase mapDatabase;

    @Autowired
    public PortfolioIntergrationTest(PortfolioOverviewController portfolioOverviewController, MapDatabase mapDatabase,
                                     PortfolioOverviewService portfolioOverviewService) {
        this.portfolioOverviewController = portfolioOverviewController;
        this.mapDatabase = mapDatabase;
        this.portfolioOverviewService = portfolioOverviewService;
    }

/*    @Test
    public void getPortfolioValueToday() {
        String token = "testtoken";
        mapDatabase.insertTokenWithHash(token, "TestUser176" );
        PortfolioValueOverview expected = new PortfolioValueOverview(LocalDate.now(), 381.57);
        ResponseEntity<Object> actual = portfolioOverviewController.getPortfolioValueToday(token);
        assertThat(actual).isEqualTo(expected);

    }

    @Test
    void testGetPortfolioOverviewToday() {
        PortfolioValueOverview expected = new PortfolioValueOverview(LocalDate.now(),381.57);
        PortfolioValueOverview actual = portfolioOverviewService.getPortfolioOverviewToday("TestUser176");
        assertThat(actual).isEqualTo(expected);

    }*/
}
