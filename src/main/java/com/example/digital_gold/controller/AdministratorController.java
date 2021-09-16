package com.example.digital_gold.controller;

/**
 * @author Shaun van Ouwerkerk
 */

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

    private final Logger logger = LoggerFactory.getLogger(AdministratorController.class);

    @Autowired
    public AdministratorController(LoginService loginService, AuthenticatorService authenticatorService) {
        this.loginService = loginService;
        this.authenticatorService = authenticatorService;
        logger.info("New AdministratorController");
    }


    @PostMapping("/adjuststartingcapital")
    public ResponseEntity<?> adjustStartingCapital(@RequestParam Double startingBudget) {
        double budget = startingBudget;
        System.out.println("Dit is het budget" + budget);
        if (budget != 0) {
            return ResponseEntity.created(URI.create("/adminstrator")).body(budget);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect request");
        }
    }

    @PostMapping("/adjusttransactionfee")
    public ResponseEntity<?> adjustTransactionFee(@RequestParam Double transactionFee) {
        double feePercentage = transactionFee;
        System.out.println("Dit is de transactionfee" + transactionFee);
        if (feePercentage != 0) {
            return ResponseEntity.created(URI.create("/administrator")).body(transactionFee);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect request");
        }
    }

    @GetMapping ("/currentstartingcapital")
    public ResponseEntity<?> currentStartingCapital() {
        double currentStartingCapital = 25;
        System.out.println("Methode currentStartCapital werkt");

        if(currentStartingCapital!= 0 ) {
            return ResponseEntity.status(HttpStatus.OK).body("Correct request");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect request");
        }
    }

    @GetMapping ("/currenttransactionfee")
    public ResponseEntity<?> currentTransactionFee() {
        double currentTransactionFee = 0.05;
        System.out.println("Methode currentTransactionfee werkt");

        if(currentTransactionFee != 0 ) {
            return ResponseEntity.status(HttpStatus.OK).body("Correct request");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect request");
        }
    }

}





