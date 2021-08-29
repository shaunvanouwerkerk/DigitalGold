package com.example.digital_gold.service;
/**
 * @Author Shaun van Ouwerkerk
 */

import com.example.digital_gold.repository.RootRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticatorService {
    private final HashService hashService;
    private RootRepository rootRepository;

    @Autowired
    public AuthenticatorService(HashService hashService, RootRepository rootRepository) {
        this.hashService = hashService;
        this.rootRepository = rootRepository;
    }
}
