package com.example.digital_gold.controller;

/**
 * @author Shaun van Ouwerkerk
 */

import com.example.digital_gold.service.AdministratorService;
import com.example.digital_gold.service.AuthenticatorService;
import com.example.digital_gold.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


@RestController
public class AdministratorController {

    private LoginService loginService;
    private AuthenticatorService authenticatorService;
    private AdministratorService administratorService;

    private final Logger logger = LoggerFactory.getLogger(AdministratorController.class);

    @Autowired
    public AdministratorController(LoginService loginService, AuthenticatorService authenticatorService, AdministratorService administratorService) {
        this.loginService = loginService;
        this.authenticatorService = authenticatorService;
        this.administratorService = administratorService;
        logger.info("New AdministratorController");
    }


    @PostMapping("/adjuststartingcapital")
    public ResponseEntity<?> adjustStartingCapital(@RequestHeader("Authorization") String token,
                                                   @RequestParam Double startingBudget) {
        administratorService.updateStartingBudget(token,startingBudget);
        double startingCapitalNew = startingBudget;

        if (startingCapitalNew!= 0) {
            return ResponseEntity.created(URI.create("/adminstrator")).body(startingCapitalNew);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect request");
        }
    }

    @PostMapping("/adjusttransactionfee")
    public ResponseEntity<?> adjustTransactionFee(@RequestHeader("Authorization") String token,
                                                  @RequestParam Double transactionFee) {
        administratorService.updateTransactionFee(token,transactionFee);
        double feePercentage = transactionFee;

        if (feePercentage != 0) {
            return ResponseEntity.created(URI.create("/administrator")).body(transactionFee);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect request");
        }
    }

    @GetMapping ("/currentstartingcapital")
    public ResponseEntity<?> currentStartingCapital(@RequestHeader("Authorization") String token) {
        double currentStartingCapital = administratorService.getStartingBudgetByUsername(token);

        if(currentStartingCapital!= 0 ) {
            return ResponseEntity.status(HttpStatus.OK).body(currentStartingCapital);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect request");
        }
    }

    @GetMapping ("/currenttransactionfee")
    public ResponseEntity<?> currentTransactionFee(@RequestHeader("Authorization") String token) {
        double currentTransactionFee = administratorService.getTransactionFeeByUsername(token);

        if(currentTransactionFee != 0 ) {
            return ResponseEntity.status(HttpStatus.OK).body(currentTransactionFee);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect request");
        }
    }

}





