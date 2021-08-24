package com.example.digital_gold.helper;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class SaltMaker {
    private static final int SALT_LENGTH = 8;
    private int saltLength;
    private SecureRandom secureRNG;

    public SaltMaker(int saltLength) {
        this.saltLength = saltLength;
        secureRNG = new SecureRandom();
    }

    public SaltMaker() {
        this(SALT_LENGTH);
    }

    public String generateSalt() {
        int tempLengte = saltLength / 2;
        byte[] arr = new byte[saltLength % 2 == 0 ? tempLengte : tempLengte + 1]; // 1 byte geeft 2 karakters, bij oneven lengte geet integer deling onderwaarde
        secureRNG.nextBytes(arr);
        String salt = ByteArrayToHexHelper.encodeHexString(arr);
        return saltLength % 2 == 0 ? salt : salt.substring(1); // als oneven is er 1 karakter teveel, haal deze weg
    }

    public void setlength(int saltLength) {
        this.saltLength = saltLength;
    }
}
