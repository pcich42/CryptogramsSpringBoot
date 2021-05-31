package com.example.cryptogramgamewithspring.Cryptogram;

import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Optional;

@Repository
public class CryptogramRepository {
    public Optional<Cryptogram> generateCryptogram(String type) throws IOException {
        if (type.equals("invalid")) return Optional.empty();
        return Optional.of(new Cryptogram("a"));
    }

    public Optional<Cryptogram> loadCryptogram(String username) throws IOException {
        return Optional.of(new Cryptogram("a"));
    }
}
