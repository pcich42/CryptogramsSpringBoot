package com.example.cryptogramgamewithspring.Commands.Factories;

import com.example.cryptogramgamewithspring.Commands.Commands.Command;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandFactory<T> {

    Command<T> fetchCommand(T context);

}
