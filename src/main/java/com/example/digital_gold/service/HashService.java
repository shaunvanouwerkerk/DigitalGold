package com.example.digital_gold.service;

import com.example.digital_gold.helper.HashHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HashService {
    private static final int DEFAULT_ROUNDS = 4;
    private final PepperService pepperService;
    private int rounds;

    @Autowired
    public HashService(PepperService pepperService) {
        this(pepperService, DEFAULT_ROUNDS);
    }

    public HashService(PepperService pepperService, int rounds) {
        this.pepperService = pepperService;
        this.rounds = rounds;
    }

    public String hash(String passwordWithSalt) {
        String hash = HashHelper.hash(passwordWithSalt, pepperService.getPepper());
        // key stretch; aantal rounds uitvoeren
        return processRounds(hash, numberOfRounds(DEFAULT_ROUNDS));
    }

    private String processRounds(String hash, long rounds) {
        for (long i = 0; i < rounds; i++) {
            hash = HashHelper.hash(hash);
        }
        return hash;
    }

    // int naar long
    private long numberOfRounds(int rounds){
        int base = 10;
        long result = base;

        for (int i = 0; i < rounds; i++) {
            result *= base;
        }
        return result;
    }
}
