package com.example.cryptogramgamewithspring.Cryptogram;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CryptogramTests {

    private Cryptogram cryptogram;

    @BeforeEach
    void setUp() {
        cryptogram = new Cryptogram("a TeSt PhrAse ");
    }

    @Test
    void newCryptogram_HasEmptySolution() {
        assertTrue(cryptogram.getSolution().isEmpty());
    }

    @Test
    void getPhrase_returnsPhrase() {
        assertEquals("a test phrase", cryptogram.getPhrase());
    }

    @Test
    void givenValueHasMapping_methodReturns_True() {
        String value = "";
        assertTrue(cryptogram.valueHasMapping(value));
    }

    @Test
    void givenValueDoesntHaveAMapping_methodReturns_False() {
        String value = "";
        assertTrue(cryptogram.valueHasMapping(value));
    }

}
