package com.example.cryptogramgamewithspring.Cryptogram;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CryptogramRepository {
    public Optional<Cryptogram> generateCryptogram(String[] input) {
        return Optional.of(new Cryptogram("a"));
    }
}
