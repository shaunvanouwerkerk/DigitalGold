package com.example.digital_gold.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
// David
class HashServiceTest {

    @Test
    void hashReturnedIsNotNull(){
        HashService hashService = new HashService(new PepperService());
        String hash = hashService.hash("TestString");
        assertNotNull(hash);
    }

    @Test
    void hashIsDeterministic() {
        HashService hashService1 = new HashService(new PepperService());
        String hash1 = hashService1.hash("TestString");
        HashService hashService2 = new HashService(new PepperService());
        String hash2 = hashService2.hash("TestString");
        String hash3 = hashService1.hash("Teststrin");
        assertEquals(hash1,hash2);
        assertNotEquals(hash1,hash3);
        assertTrue(hash1.length() == 64 && hash2.length() == 64 && hash3.length() == 64);
    }
}