package com.example.digital_gold.service;

import org.springframework.stereotype.Service;

@Service
public class PepperService {
    private static final String PEPPER = "EenApplicatieMoetNietTeFlauwZijn";

    public String getPeper() {
        return PEPPER;
    }
}
