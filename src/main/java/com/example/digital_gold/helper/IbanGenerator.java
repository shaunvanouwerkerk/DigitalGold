package com.example.digital_gold.helper;
import java.util.Random;

public class IbanGenerator {
    private static final int MOD = 97;
    private static final long MAX = 999999999;
    private final static String COUNTRY_CODE_NL = "NL";
    private final static String DEFAULT_CHECK_DIGIT = "00";
    private final static String BANK_CODE = "DIGO";
    private String checkDigit;
    private String accountNumber;


    public String generateIban() {
        this.accountNumber = generateAccountNumber();
        this.checkDigit = calculateCheckDigit(accountNumber);
        return COUNTRY_CODE_NL + checkDigit + BANK_CODE + accountNumber;
    }


    public String generateAccountNumber() {
        Random rnd = new Random();
        int number1 = rnd.nextInt(99999);
        int number2 = rnd.nextInt(99999);
        return String.format("%05d%05d", number1, number2);
    }


    public static String calculateCheckDigit(String accountNumber)  {
        final int modResult = calculateMod(accountNumber);
        final int checkDigitIntValue = (98 - modResult);
        final String checkDigit = Integer.toString(checkDigitIntValue);
        return checkDigitIntValue > 9 ? checkDigit : "0" + checkDigit;
    }


    private static int calculateMod(String accountNumber) {
        String reformattedIban = BANK_CODE + accountNumber + COUNTRY_CODE_NL + DEFAULT_CHECK_DIGIT;
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
