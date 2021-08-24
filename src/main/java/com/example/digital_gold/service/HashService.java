package com.example.digital_gold.service;

import com.example.digital_gold.helper.HashHelper;
import com.example.digital_gold.service.PepperService;
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
        // eventueel controleren op te grote waarden (< 6)
    }

    public String hash(String value) {
        String hash = HashHelper.hash(value, pepperService.getPeper()); // hacks! gebruik pepper in plaats van salt
        // pas eventueel een key stretch toe: x ronden uitvoeren
        return processRounds(hash, numberOfRounds(rounds));
    }

    private String processRounds(String hash, long r) {
        for (long i = 0; i < r; i++) {
            // niet zo efficient om dit met String te doen en HashHelper hash maakt ook steeds nieuwe objecten aan
            // wordt wel al heel snel erg traag
            hash = HashHelper.hash(hash);
        }
        return hash;
    }

    // Math.pow geeft een double terug, een long is gewenst
    // om testbaar te maken, naar eigen klasse toe zetten
    private long numberOfRounds(int load){
        int base = 10;
        long result = base; // base ^ 1

        for (int i = 0; i < load; i++) {
            result *= base;
        }
        return result;
    }
}
