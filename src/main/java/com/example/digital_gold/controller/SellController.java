package com.example.digital_gold.controller;

import com.example.digital_gold.domain.Transaction;
import com.example.digital_gold.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * @author Sandra Turina
 */

@RestController
public class SellController {
    private OrderService orderService;
    private AuthenticatorService authenticatorService;


    private final Logger logger = LoggerFactory.getLogger(SellController.class);

    @Autowired
    public SellController(OrderService orderService, AuthenticatorService authenticatorService) {
        this.orderService = orderService;
        this.authenticatorService = authenticatorService;
        logger.info("New SellController");

    }

        @PostMapping("/sell")
    public ResponseEntity<?> placeOrder(@Valid @RequestBody Order requestOrder) {
        logger.info("Uit body via JSON aangemaakt: " + requestOrder);
        Transaction orderTransaction = orderService.processOrder(requestOrder);
        if (orderTransaction != null) {
            return ResponseEntity.created(URI.create("/sell")).build();
        } else {
            return ResponseEntity.internalServerError().body("Your order cannot be processed at the moment. " +
                    "Please try again later");
        }
    }
}
