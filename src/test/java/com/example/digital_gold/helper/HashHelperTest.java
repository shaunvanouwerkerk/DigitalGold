package com.example.digital_gold.helper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
*  @author Fiona Gray
* */
class HashHelperTest {

    @Test
    void testHashWithSaltAndPepper() {
        String hash = HashHelper.hash("digitalWachtwoordMetZout", "DigForGoldHere");
        assertEquals("74010f701a53e02c0f72c78a34ae9b8d92aad49d69aed0f2d0125771a5deaaf1", hash);

        String hash2 = HashHelper.hash("goldDiggerWithSalt", "DigForGoldHere");
        assertEquals("a2d7bbc44046044dec9c66aac87b60b126b057431aaa74dc68d5d6491d7360fe", hash2);

        String hash3 = HashHelper.hash("ikWordRijkKorreltjeZout", "DigForGoldHere");
        assertEquals("ca36bbbb689a0c92eb7087791dab7d78e752ee1ee6320c98e81c486639402ec1", hash3);

        // test of er twee keer dezelfde hash uitkomt
        String hash4 = HashHelper.hash("ikWordRijkKorreltjeZout", "DigForGoldHere");
        assertEquals(hash4, hash3);

        // test met andere pepper
        String hash5= HashHelper.hash("ikWordRijkKorreltjeZout", "AddSomeSpice");
        assertEquals("4a63d283e6161b066a1936238c9f6ab1249a24f7c6457b4faf493fb8077f4db4", hash5);
    }
}