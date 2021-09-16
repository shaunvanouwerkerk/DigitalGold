package com.example.digital_gold.helper;
import java.math.BigInteger;
import java.util.Random;

public class IbanGenerator<BigInteger97> {

    private final static String COUNTRY_CODE_NL = "NL";
    private final static String COUNTRY_CODE_NL_NUMERIC = "2321";
    private final static String DEFAULT_CHECK_DIGIT = "00";
    private final static String BANK_CODE = "DIGO";
    private final static String BANK_CODE_NUMERIC = "13181624";

    public String accountNumber;

    public String generateIban() {
        this.accountNumber = generateAccountNumber();
        return createIbanFromAccountNumber(accountNumber);
    }

    public String generateAccountNumber() {
        Random rnd = new Random();
        int number1 = rnd.nextInt(99999);
        int number2 = rnd.nextInt(99999);
        return String.format("%05d%05d", number1, number2);
    }

    public String createIbanFromAccountNumber(String accountNumber) {
        String checkSum;
        BigInteger iban =
                new BigInteger(BANK_CODE_NUMERIC + accountNumber + COUNTRY_CODE_NL_NUMERIC + DEFAULT_CHECK_DIGIT);
        BigInteger BigIntOf97 = BigInteger.valueOf(97);
        BigInteger rest = iban.mod(BigIntOf97);
        BigInteger BigIntOf98 = BigInteger.valueOf(98);
        BigInteger remainder = BigIntOf98.subtract(rest);
        String checkDigit = remainder.toString();
        if (checkDigit.length() < 2) {
            checkSum =  "0" + checkDigit;
        } else {
            checkSum = checkDigit;
        }
        return COUNTRY_CODE_NL + checkSum + BANK_CODE + accountNumber;
    }
}
