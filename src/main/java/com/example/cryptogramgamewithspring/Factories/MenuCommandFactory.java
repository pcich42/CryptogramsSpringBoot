package com.example.cryptogramgamewithspring.Factories;

import com.example.cryptogramgamewithspring.Interfaces.CommandFactory;
import com.example.cryptogramgamewithspring.Interfaces.MenuCommand;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Repository
public class MenuCommandFactory implements CommandFactory<MenuCommand> {

    private final Map<String, Supplier<? extends MenuCommand>> commands;

    public MenuCommandFactory() {
        commands = new HashMap<>();
//        commands.put("new", () -> new playGeneratedCryptogramGameCommand(player, playerList, manager, view, prompt, input, gameCommands));
//        commands.put("load", () -> new playLoadedCryptogramGameCommand(player, playerList, manager, view, prompt, gameCommands));
////        commands.put("login", () -> new changePlayerCommand(playerList));
//        commands.put("scores", () -> new showScoreboardCommand(playerList, view));
    }


    @Override
    public MenuCommand fetchCommand(String[] input) {
        return commands.getOrDefault(input[0], () -> () -> System.out.println("gowno")).get();
    }
}
