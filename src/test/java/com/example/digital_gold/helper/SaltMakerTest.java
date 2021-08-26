package com.example.digital_gold.helper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
* @author Fiona Gray
* */

class SaltMakerTest {

    @Test
    public void generateSalt_default_length(){
        // salt met 8 characters
        SaltMaker saltMaker = new SaltMaker();
        String result = saltMaker.generateSalt();
        assertEquals(8, result.length());
    }
}