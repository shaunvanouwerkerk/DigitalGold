package com.example.digital_gold.helper;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public SaltMaker() {
        this(SALT_LENGTH);
    }

    public String generateSalt() {
        int tempLength = saltLength / 2;
        byte[] array = new byte[saltLength % 2 == 0 ? tempLength : tempLength + 1];
        secureRNG.nextBytes(array);
        String salt = ByteArrayToHexHelper.encodeHexString(array);
        return saltLength % 2 == 0 ? salt : salt.substring(1);
    }

    // for testing purposes
    public void setlength(int saltLength) {
        this.saltLength = saltLength;
    }
}
