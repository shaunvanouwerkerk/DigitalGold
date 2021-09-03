package com.example.digital_gold.helper;

import java.util.Random;
/**
 *  @author Sandra Turina
 * */

public class IbanGenerator {
    final static String COUNTRY_CODE_NL = "NL";
    private String initialNumbers;
    final static String BANK_CODE = "DIGO";
    private String accountNumber;


    public String generateIban() {
        this.initialNumbers = generateInitialNumbers();
        this.accountNumber = generateAccountNumber();
        return COUNTRY_CODE_NL + initialNumbers + BANK_CODE + accountNumber;
    }

    // TODO een ipv 2 methodes?

    public String generateInitialNumbers() {
        Random rnd = new Random();
        int number = rnd.nextInt(99);
        return String.format("%02d", number);
    }

    public String generateAccountNumber() {
        Random rnd = new Random();
        int number1 = rnd.nextInt(99999);
        int number2 = rnd.nextInt(99999);
        return String.format("%05d%05d", number1, number2);
    }

}
