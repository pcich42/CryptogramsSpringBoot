package com.example.cryptogramgamewithspring.Commands.Factories;

import com.example.cryptogramgamewithspring.Commands.Commands.Command;
import com.example.cryptogramgamewithspring.Controllers.Context;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandFactory<T extends Context> {

    Command<T> fetchCommand(T context);

}
