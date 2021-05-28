package com.example.cryptogramgamewithspring.Controllers;

import com.example.cryptogramgamewithspring.Commands.Commands.Command;
import com.example.cryptogramgamewithspring.Commands.Factories.CommandFactory;
import com.example.cryptogramgamewithspring.Player.InvalidFileFormatException;
import com.example.cryptogramgamewithspring.Presentation.InputPrompt;
import com.example.cryptogramgamewithspring.Presentation.ConsoleView;
import com.example.cryptogramgamewithspring.Player.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;

@Component
public class Menu implements GameController {

    private final InputPrompt prompt;
    private final ConsoleView view;
    private final PlayerService players;
    private final CommandFactory<Command<MenuContext>> commandFactory;

    public Menu(MenuContext context) {
        this.prompt = context.getPrompt();
        this.view = context.getView();
        this.players = context.getPlayers();
        this.commandFactory = context.getCommandFactory();
    }

    public void run() {
        view.displayMessage("Welcome to the Cryptograms Game!");
    }

    public void mainLoop() {
        while (true) {
            view.displayMessage("Choose what you want to do next.");
            String[] input = prompt.getInput();
            if (input[0].contains("exit")) break;
            fetchAndExecuteCommand(input);
        }
    }

    private void fetchAndExecuteCommand(String[] input) {
        Command<MenuContext> command = commandFactory.fetchCommand(input);
        command.execute();
    }

    public boolean loadPlayers() {
        try {
            players.loadPlayersFromFile();
        } catch (FileNotFoundException notFoundException) {
            view.displayMessage("Players file not found. Exiting...");
            return false;
        } catch (IOException | InvalidFileFormatException e) {
            view.displayMessage("File format invalid. Exiting...");
            return false;
        }
        return true;
    }
}
