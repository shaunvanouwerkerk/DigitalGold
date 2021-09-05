package com.example.digital_gold.controller;

import com.example.digital_gold.domain.Portfolio;
import com.example.digital_gold.service.PortfolioOverviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

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

/*    @GetMapping("/portfoliooverview/{username}")
        public ResponseEntity<List<PortfolioHistory>> getOverviewAssets(@PathVariable String username) {
        return new ResponseEntity<>(portfolioOverviewService.getOverviewAssets(username), HttpStatus.OK);
        }*/

    @GetMapping("/portfoliooverview/{username}")
    public ResponseEntity<?> getOverviewAssets(@PathVariable String username) {
        return new ResponseEntity(portfolioOverviewService.getPortfolioForCustomer(username), HttpStatus.OK);
    }
}
