package com.example.digital_gold.helper;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashHelper {

    public static final String SHA_256 = "SHA-256";
    public static final String ALGORITME_BESTAAT_NIET = "Het opgegeven algoritme bestaat niet";

    public static String hash(String value) {
        try {
            MessageDigest sha = MessageDigest.getInstance(SHA_256); // hash aanmaken m.b.v. sha-256 hashing algoritme
            sha.update(value.getBytes(StandardCharsets.UTF_8));
            byte[] digest = sha.digest();
            return ByteArrayToHexHelper.encodeHexString(digest);
        } catch (NoSuchAlgorithmException exception) {
            exception.printStackTrace();
            throw new RuntimeException(ALGORITME_BESTAAT_NIET);
        }
    }

    public static String hash(String passwordWithSalt, String pepper) {
        try {
            MessageDigest sha = MessageDigest.getInstance(SHA_256);
            sha.update(passwordWithSalt.getBytes(StandardCharsets.UTF_8));
            sha.update(pepper.getBytes(StandardCharsets.UTF_8));
            byte[] digest = sha.digest();
            return ByteArrayToHexHelper.encodeHexString(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException(ALGORITME_BESTAAT_NIET);
        }
    }
}
