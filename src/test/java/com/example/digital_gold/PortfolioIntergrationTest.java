package com.example.digital_gold;

import com.example.digital_gold.controller.PortfolioOverviewController;
import com.example.digital_gold.repository.MapDatabase;
import com.example.digital_gold.service.PortfolioValueOverview;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

@SpringBootTest
@ActiveProfiles("test")
public class PortfolioIntergrationTest {

    PortfolioOverviewController portfolioOverviewController;
    MapDatabase mapDatabase;

    @Autowired
    public PortfolioIntergrationTest(PortfolioOverviewController portfolioOverviewController, MapDatabase mapDatabase) {
        this.portfolioOverviewController = portfolioOverviewController;
        this.mapDatabase = mapDatabase;
    }

    @Test
    public void getPortfolioValueToday() {
        String token = "testtoken";
        mapDatabase.insertTokenWithHash(token, "TestUser175" );

        PortfolioValueOverview expected = new PortfolioValueOverview(LocalDate.now(), 100.99);
        ResponseEntity actual = portfolioOverviewController.getPortfolioValueToday(token);
        System.out.println(expected);
        System.out.println(actual);

    }
}
