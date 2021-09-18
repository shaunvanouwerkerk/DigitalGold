package com.example.digital_gold.controller;

import com.example.digital_gold.service.AuthenticatorService;
import com.example.digital_gold.service.PortfolioAssetOverview;
import com.example.digital_gold.service.PortfolioOverviewService;
import com.example.digital_gold.service.PortfolioValueOverview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
@Author Jany Gaal
*/

@RestController
public class PortfolioOverviewController {

    private final PortfolioOverviewService portfolioOverviewService;
    private final AuthenticatorService authenticatorService;

    @Autowired
    public PortfolioOverviewController(PortfolioOverviewService portfolioOverviewService, AuthenticatorService authenticatorService) {
        this.portfolioOverviewService = portfolioOverviewService;
        this.authenticatorService = authenticatorService;
    }

    @GetMapping("/portfoliovalueoverviewtoday")
    public @ResponseBody
    ResponseEntity<Object> getPortfolioValueToday(@RequestHeader("Authorization") String token) {
        String username = authenticatorService.authenticateUsername(token);
        if (!(username == null)) {
            return new ResponseEntity<>(portfolioOverviewService.getPortfolioOverviewToday(username), HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/portfoliovalueoverview")
    public @ResponseBody
    ResponseEntity<List<PortfolioValueOverview>> getPortfolioValueOverview(@RequestHeader("Authorization") String token) {
        String username = authenticatorService.authenticateUsername(token);
        if (!(username == null)) {
            return new ResponseEntity<>(portfolioOverviewService.getPortfolioOverview(username), HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/portfolioassetoverview")
    public @ResponseBody
    ResponseEntity<List<PortfolioAssetOverview>> getPortfolioOverviewAssets(@RequestHeader("Authorization") String token) {
        String username = authenticatorService.authenticateUsername(token);
        if (!(username == null)) {
            return new ResponseEntity<>(portfolioOverviewService.getPortfolioOverviewAssets(username), HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}


