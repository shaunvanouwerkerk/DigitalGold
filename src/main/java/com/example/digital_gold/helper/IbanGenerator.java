package com.example.digital_gold.helper;
//import org.apache.commons.validator.routines.checkdigit.IBANCheckDigit;


import java.util.Random;

import java.io.Serializable;
/**
 *  @author Sandra Turina
 * */
//extends CheckDigit
public class IbanGenerator {
    private static final int MOD = 97;
    private static final long MAX = 999999999;
    final static String COUNTRY_CODE_NL = "NL";
    static final String DEFAULT_CHECK_DIGIT = "00";
    private String checkDigit;
    final static String BANK_CODE = "DIGO";
    private String accountNumber;


    public String generateIban() {
        this.checkDigit = generateInitialNumbers();
        this.accountNumber = generateAccountNumber();
        return COUNTRY_CODE_NL + checkDigit + BANK_CODE + accountNumber;
    }

    // TODO een ipv 2 methodes?

    public String generateInitialNumbers() {
        Random rnd = new Random();
        int number = rnd.nextInt(99);
        return String.format("%02d", number);
    }

    public static String calculateCheckDigit(final String iban)  {
        final int modResult = calculateMod(iban);
        final int checkDigitIntValue = (98 - modResult);
        final String checkDigit = Integer.toString(checkDigitIntValue);
        return checkDigitIntValue > 9 ? checkDigit : "0" + checkDigit;
    }

    public String generateAccountNumber() {
        Random rnd = new Random();
        int number1 = rnd.nextInt(99999);
        int number2 = rnd.nextInt(99999);
        return String.format("%05d%05d", number1, number2);
    }

    /**
     * Calculates
     * <a href="http://en.wikipedia.org/wiki/ISO_13616#Modulo_operation_on_IBAN">Iban Modulo</a>.
     *
     * @param accountNumber String value
     * @return modulo 97
     */
    private static int calculateMod(final String accountNumber) {
        final String reformattedIban = accountNumber + COUNTRY_CODE_NL + DEFAULT_CHECK_DIGIT;
        long total = 0;
        for (int i = 0; i < reformattedIban.length(); i++) {
            final int numericValue = Character.getNumericValue(reformattedIban.charAt(i));

            total = (numericValue > 9 ? total * 100 : total * 10) + numericValue;

            if (total > MAX) {
                total = (total % MOD);
            }

        }
        return (int) (total % MOD);
    }


}
