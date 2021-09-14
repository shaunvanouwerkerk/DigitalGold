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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
    public ResponseEntity<PortfolioValueOverview> getPortfolioValueOverviewToday(@RequestHeader("Authorization") String token) {
        String username = authenticatorService.authenticateUsername(token);
        if (!(username == null)) {
            PortfolioValueOverview portfolioValueOverview = portfolioOverviewService.getPortfolioOverviewToday(username);
            if (!(portfolioValueOverview == null)) {
                return new ResponseEntity<>(portfolioValueOverview, HttpStatus.OK);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Data Found");
            }
        } else throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/portfoliovalueoverview")
    public ResponseEntity<List<PortfolioValueOverview>> getPortfolioValueOverview(@RequestHeader("Authorization") String token) {
        String username = authenticatorService.authenticateUsername(token);
        if (!(username == null)) {
            List<PortfolioValueOverview> portfolioValueOverviewList = portfolioOverviewService.getPortfolioOverview(username);
            if (!(portfolioValueOverviewList == null)) {
                return new ResponseEntity<>(portfolioValueOverviewList, HttpStatus.OK);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Data Found");
            }
        } else throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/portfolioassetoverview")
    public ResponseEntity<List<PortfolioAssetOverview>> getPortfolioOverviewAssets(@RequestHeader("Authorization") String token) {
        String username = authenticatorService.authenticateUsername(token);
        if (!(username == null)) {
            List<PortfolioAssetOverview> portfolioAssetOverviewList = portfolioOverviewService.getPortfolioOverviewAssets(username);
            if (!(portfolioAssetOverviewList == null)) {
                return new ResponseEntity<>(portfolioAssetOverviewList, HttpStatus.OK);
            } else
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Data Found");
        } else throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

    }
}

