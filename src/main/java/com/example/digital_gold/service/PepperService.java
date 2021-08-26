package com.example.digital_gold.service;

import org.springframework.stereotype.Service;

@Service
public class PepperService {
    private static final String PEPPER = "DigForGoldHere";

    public String getPepper() {
        return PEPPER;
    }
}
