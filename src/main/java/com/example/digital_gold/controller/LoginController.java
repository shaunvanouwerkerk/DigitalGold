package com.example.digital_gold.controller;

/**
 * @author Shaun van Ouwerkerk
 */

import com.example.digital_gold.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;


@RestController
public class LoginController {

    private LoginService loginService;

    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
        logger.info("New LoginController");
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

}
