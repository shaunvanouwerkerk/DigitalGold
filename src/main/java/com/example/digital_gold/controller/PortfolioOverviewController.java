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

    @GetMapping("/portfoliovalueoverviewtoday/{username}")
    public ResponseEntity<PortfolioValueOverview> getPortfolioValueOverviewToday (@PathVariable String username) {
        return new ResponseEntity<>(portfolioOverviewService.getPortfolioOverviewToday(username), HttpStatus.OK);
    }

        @GetMapping("/portfoliovalueoverview/{username}")
        public ResponseEntity<List<PortfolioValueOverview>> getPortfolioValueOverview(@PathVariable String username) {
        return new ResponseEntity<>(portfolioOverviewService.getPortfolioOverview(username),
                HttpStatus.OK);
    }

    @GetMapping("/portfolioassetoverview/{username}")
        public ResponseEntity<List<PortfolioAssetOverview>> getPortfolioOverviewAssets(@PathVariable String username) {
        return new ResponseEntity<>(portfolioOverviewService.getPortfolioOverviewAssets(username), HttpStatus.OK);
        }

}
