package com.example.digital_gold.controller;

/**
 * @author Shaun van Ouwerkerk
 */

import com.example.digital_gold.service.AuthenticatorService;
import com.example.digital_gold.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;


@Controller
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


    @PutMapping("/login")
    public ResponseEntity<?> loginCustomer(@RequestParam String username, @RequestParam String password) {
        String token = loginService.login(username,password);

        if(token != null) {
            return ResponseEntity.created(URI.create("/login")).body("Token created" + token);
        } else {
            return ResponseEntity.badRequest().body("Token not created");
        }
    }
    @PutMapping("/login2")
    public ResponseEntity<?> loginAndAuthenticateCustomer(@RequestParam String token) {
        boolean tokenRecognized = authenticatorService.authenticate(token);

        if(tokenRecognized) {
            return ResponseEntity.created(URI.create("/login2")).body("User Known");
        } else {
            return ResponseEntity.badRequest().body("UserUnknown");
        }
    }
    @PutMapping("/login/administrator")
    public ResponseEntity<?> loginAdministrator(@RequestParam String username, @RequestParam String password) {
        String token = loginService.loginAdministrator(username,password);

        if(token != null) {
            return ResponseEntity.created(URI.create("/login")).body("Token created" + token);
        } else {
            return ResponseEntity.badRequest().body("Token not created");
        }
    }

}
