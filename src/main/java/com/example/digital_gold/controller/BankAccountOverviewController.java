package com.example.digital_gold.controller;

import com.example.digital_gold.service.AuthenticatorService;
import com.example.digital_gold.service.BankAccountOverviewService;
import com.example.digital_gold.service.PortfolioOverviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sandra Turina
 **/

@RestController
public class BankAccountOverviewController {
    private BankAccountOverviewService bankAccountOverviewService;
    private AuthenticatorService authenticatorService;

    private final Logger logger = LoggerFactory.getLogger(BankAccountOverviewController.class);

    @Autowired
    public BankAccountOverviewController(BankAccountOverviewService bankAccountOverviewService,
                                         AuthenticatorService authenticatorService) {
        this.bankAccountOverviewService = bankAccountOverviewService;
        this.authenticatorService = authenticatorService;
        logger.info("New BankAccountOverviewController");
    }

    @GetMapping("/balance")
    public ResponseEntity<?> getCurrentBalance(@RequestHeader("Authorization") String token) {

        String username = authenticatorService.authenticateUsername(token);
        if (!(username == null)) {
            return ResponseEntity.status(HttpStatus.OK).body(bankAccountOverviewService.bankAccountOverview(username).getBalance());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
