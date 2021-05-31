package com.example.cryptogramgamewithspring.Cryptogram;

import java.util.HashMap;
import java.util.Map;

public class Cryptogram {

    // mapping from real letter to crypto value
    private final Map<String, String> solution;
    private final String phrase;

    public Cryptogram(String phrase) {
        this.solution = new HashMap<>();
        this.phrase = phrase.toLowerCase().strip();
    }

    public Map<String, String> getSolution() {
        return this.solution;
    }


    public String getPhrase() {
        return this.phrase;
    }

    public boolean valueHasMapping(String value) {
        return true;
    }

}
