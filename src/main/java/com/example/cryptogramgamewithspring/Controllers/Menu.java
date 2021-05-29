package com.example.cryptogramgamewithspring.Controllers;

import com.example.cryptogramgamewithspring.Commands.Commands.Command;
import com.example.cryptogramgamewithspring.Commands.Factories.CommandFactory;
import com.example.cryptogramgamewithspring.Cryptogram.CryptogramRepository;
import com.example.cryptogramgamewithspring.Player.InvalidFileFormatException;
import com.example.cryptogramgamewithspring.Player.Player;
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
    private final CommandFactory<MenuContext> menuCommandFactory;
    private final CommandFactory<GameContext> gameCommandFactory;
    private Player player;
    private final CryptogramRepository cryptogramRepository;

    @Autowired
    public Menu(InputPrompt prompt,
                ConsoleView view,
                PlayerService players,
                @Qualifier("MenuCommands") CommandFactory<MenuContext> menuCommandFactory,
                @Qualifier("GameplayCommands") CommandFactory<GameContext> gameCommandFactory,
                CryptogramRepository cryptogramRepository) {
        this.prompt = prompt;
        this.view = view;
        this.players = players;
        this.menuCommandFactory = menuCommandFactory;
        this.gameCommandFactory = gameCommandFactory;
        this.cryptogramRepository = cryptogramRepository;
    }

    public void run() {
        view.displayMessage("Welcome to the Cryptograms Game!");
        if(!loadPlayers()) return;
        view.displayMessage("What is your username?");
        player = players.getPlayer(prompt.getInput()[0]);
        view.displayMessage("Hello " + player.getUsername() + "!");

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
        MenuContext context = new MenuContext(prompt, view, players, gameCommandFactory, input, cryptogramRepository, player);
        Command<MenuContext> command = menuCommandFactory.fetchCommand(context);
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
