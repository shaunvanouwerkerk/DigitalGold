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
public class AuthenticatedController {

    private LoginService loginService;
    private AuthenticatorService authenticatorService;

    private final Logger logger = LoggerFactory.getLogger(AuthenticatedController.class);

    @Autowired
    public AuthenticatedController(LoginService loginService, AuthenticatorService authenticatorService) {
        this.loginService = loginService;
        this.authenticatorService = authenticatorService;
        logger.info("New AuthenticatedController");
    }

    @GetMapping ("/authenticate")
    public ResponseEntity<?> list(@RequestHeader("Authorization") String token) {
        boolean tokenRecognized = authenticatorService.authenticate(token);

        String username = authenticatorService.authenticateUsername(token);
        System.out.println(" Dit is de username vanuit authenticate controller" + username);

        if(tokenRecognized) {
            return ResponseEntity.status(HttpStatus.OK).body("User Known");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User unknown");
        }
    }

}

