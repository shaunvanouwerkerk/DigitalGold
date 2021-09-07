package com.example.digital_gold.controller;

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

/**
 * @author Sandra Turina
 **/
@Controller
public class BankAccountOverviewController {

    private BankAccountOverviewService bankAccountOverviewService;

    private final Logger logger = LoggerFactory.getLogger(BankAccountOverviewController.class);

    @Autowired
    public BankAccountOverviewController(BankAccountOverviewService bankAccountOverviewService) {
        this.bankAccountOverviewService = bankAccountOverviewService;
    }

    @GetMapping("/bankaccountoverview/{username}")
    public ResponseEntity<?> getOverviewAssets(@PathVariable String username) {
        return new ResponseEntity(bankAccountOverviewService.bankAccountOverview(username), HttpStatus.OK);
    }

}
