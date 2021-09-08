package com.example.digital_gold.controller;

import com.example.digital_gold.service.PortfolioAssetOverview;
import com.example.digital_gold.service.PortfolioOverviewService;
import com.example.digital_gold.service.PortfolioValueOverview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
@Author Jany Gaal
*/

@RestController
public class PortfolioOverviewController {

    private PortfolioOverviewService portfolioOverviewService;

    @Autowired
    public PortfolioOverviewController(PortfolioOverviewService portfolioOverviewService) {
        this.portfolioOverviewService = portfolioOverviewService;
    }

    @GetMapping("/portfoliovalueoverviewtoday")
    public ResponseEntity<PortfolioValueOverview> getPortfolioValueOverviewToday () {
        return new ResponseEntity<>(portfolioOverviewService.getPortfolioOverviewToday("TestUser105"), HttpStatus.OK);
    }

    @GetMapping("/portfoliovalueoverview")
    public ResponseEntity<List<PortfolioValueOverview>> getPortfolioValueOverview() {
        return new ResponseEntity<>(portfolioOverviewService.getPortfolioOverview("TestUser105"),
                HttpStatus.OK);
        }

    @GetMapping("/portfolioassetoverview")
        public ResponseEntity<List<PortfolioAssetOverview>> getPortfolioOverviewAssets() {
        return new ResponseEntity<>(portfolioOverviewService.getPortfolioOverviewAssets("TestUser105"), HttpStatus.OK);
        }

}
