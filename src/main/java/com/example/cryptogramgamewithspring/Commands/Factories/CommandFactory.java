package com.example.cryptogramgamewithspring.Commands.Factories;

import org.springframework.stereotype.Repository;

@Repository
public interface CommandFactory<T> {

    T fetchCommand(String[] input);

}
