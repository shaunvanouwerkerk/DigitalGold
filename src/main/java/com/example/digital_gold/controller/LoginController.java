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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


@RestController
public class LoginController {

    private LoginService loginService;
    private AuthenticatorService authenticatorService;

    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    public LoginController(LoginService loginService, AuthenticatorService authenticatorService) {
        this.loginService = loginService;
        this.authenticatorService = authenticatorService;
        logger.info("New LoginController");
    }

    @GetMapping("/")
    String login() {
        System.out.println("HALLO LOGIN");
        return "login";
    }


    @PostMapping("/login")
    public ResponseEntity<?> loginCustomer(@RequestParam String username, @RequestParam String password) {
        String token = loginService.login(username,password);
        if(token != null) {
            return ResponseEntity.created(URI.create("/login")).body(token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect credentials");
        }
    }

    @PutMapping("/login/administrator")
    public ResponseEntity<?> loginAdministrator(@RequestParam String username, @RequestParam String password) {
        String token = loginService.login(username,password);

        if(token != null) {
            return ResponseEntity.created(URI.create("/login")).body(token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect credentials");
        }
    }

}
