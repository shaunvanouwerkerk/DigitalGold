package com.example.digital_gold.helper;

import com.example.digital_gold.domain.BankAccount;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 *  @author Sandra Turina
 * */

public class IbanGeneratorTest {
    IbanGenerator ibanGenerator = new IbanGenerator();

    @Test
    public void generateIban() {
        String result = ibanGenerator.generateIban();
        System.out.println(result);
    }

    @Test
    public void generateDifferentIban() {
        String result1 = ibanGenerator.generateIban();
        String result2 = ibanGenerator.generateIban();
        String result3 = ibanGenerator.generateIban();
        assertNotEquals(result1, result2, result3);
        System.out.printf("%s\n%s\n%s\n", result1, result2, result3);
    }

    @Test
    public void checkLengthIban() {
        String result = ibanGenerator.generateIban();
        assertEquals(18, result.length());
    }
}
