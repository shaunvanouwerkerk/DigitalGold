package com.example.digital_gold.controller;

import com.example.digital_gold.domain.Transaction;
import com.example.digital_gold.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

/**
 @author David Truijens
 */

@RestController
public class TransactionController {

    private TransactionService transactionService;

    private final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @PostMapping("/transaction")
    public ResponseEntity<?> processTransaction(@Valid @RequestBody Transaction transaction) {
        logger.info("Uit body via JSON aangemaakt: " + transaction);
        Transaction requestedTransaction = transactionService.executeTransaction(transaction);

        if(requestedTransaction != null) {
            return ResponseEntity.created(URI.create("/transaction")).build();
        } else {
            return ResponseEntity.badRequest().body("Transaction could not be processed.");
        }
    }

    public TransactionService getTransactionService() {
        return transactionService;
    }
}
