package com.example.digital_gold.controller;

import com.example.digital_gold.service.AuthenticatorService;
import com.example.digital_gold.service.PortfolioAssetOverview;
import com.example.digital_gold.service.PortfolioOverviewService;
import com.example.digital_gold.service.PortfolioValueOverview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
@Author Jany Gaal
*/

@RestController
public class PortfolioOverviewController {

    private PortfolioOverviewService portfolioOverviewService;
    private AuthenticatorService authenticatorService;

    @Autowired
    public PortfolioOverviewController(PortfolioOverviewService portfolioOverviewService, AuthenticatorService authenticatorService) {
        this.portfolioOverviewService = portfolioOverviewService;
        this.authenticatorService = authenticatorService;
    }

    @GetMapping("/portfoliovalueoverviewtoday")
    public ResponseEntity<PortfolioValueOverview> getPortfolioValueOverviewToday(@RequestHeader("Authorization") String token) {
        String username = authenticatorService.authenticateUsername(token);
        try {
            return new ResponseEntity<>(portfolioOverviewService.getPortfolioOverviewToday(username), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/portfoliovalueoverview")
    public ResponseEntity<List<PortfolioValueOverview>> getPortfolioValueOverview(@RequestHeader("Authorization") String token) {
        String username = authenticatorService.authenticateUsername(token);
        return new ResponseEntity<>(portfolioOverviewService.getPortfolioOverview(username),
                HttpStatus.OK);
    }

    @GetMapping("/portfolioassetoverview")
    public ResponseEntity<List<PortfolioAssetOverview>> getPortfolioOverviewAssets(@RequestHeader("Authorization") String token) {
        String username = authenticatorService.authenticateUsername(token);
        return new ResponseEntity<>(portfolioOverviewService.getPortfolioOverviewAssets(username), HttpStatus.OK);
    }
}

