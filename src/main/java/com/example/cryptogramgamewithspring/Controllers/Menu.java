package com.example.cryptogramgamewithspring.Controllers;

import com.example.cryptogramgamewithspring.InputOutput.InputPrompt;
import com.example.cryptogramgamewithspring.InputOutput.View;
import com.example.cryptogramgamewithspring.Interfaces.CommandFactory;
import com.example.cryptogramgamewithspring.Interfaces.MenuCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;

@Component
public class Menu {

    private final InputPrompt prompt;
    private final View view;
    private final PlayerRepository players;
    private final CommandFactory<MenuCommand> commandFactory;

    @Autowired
    public Menu(InputPrompt prompt,
                View view,
                PlayerRepository players,
                @Qualifier("MenuCommands") CommandFactory<MenuCommand> commandFactory) {
        this.prompt = prompt;
        this.view = view;
        this.players = players;
        this.commandFactory = commandFactory;
    }

    public void run() {
        System.out.println("Welcome to the Cryptograms Game!");
    }

    public void mainLoop() {
        while (true) {
            System.out.println("Choose what you want to do next.");
            String[] input = prompt.getInput();
            if (input[0].contains("exit")) break;
            fetchAndExecuteCommand(input);
        }
    }

    private void fetchAndExecuteCommand(String[] input) {
        MenuCommand command = commandFactory.fetchCommand(input);
        command.perform();
    }

    public boolean loadPlayers() {
        try {
            players.loadPlayersFromFile();
        } catch (FileNotFoundException notFoundException) {
            view.displayMessage("Players file not found. Exiting...");
            return false;
        } catch (IOException ioException) {
            view.displayMessage("File format invalid. Exiting...");
            return false;
        }
        return true;
    }
}
