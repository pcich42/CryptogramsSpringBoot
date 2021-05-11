package com.example.cryptogramgamewithspring.Interfaces;

import org.springframework.stereotype.Repository;

@Repository
public interface CommandFactory<T> {

    T fetchCommand(String[] input);

}
