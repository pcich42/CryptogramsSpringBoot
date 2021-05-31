package com.example.cryptogramgamewithspring.Controllers;

import com.example.cryptogramgamewithspring.Controllers.Commands.*;
import com.example.cryptogramgamewithspring.Controllers.Commands.CommandSupplier.CommandSupplier;
import com.example.cryptogramgamewithspring.Player.Player;
import com.example.cryptogramgamewithspring.Presentation.ConsoleView;
import com.example.cryptogramgamewithspring.Player.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;;

@Component
public class Menu extends ConsoleCommandController {

    private final ConsoleView view;
    private final PlayerService players;
    private final CommandSupplier supplier;
    private Player player;

    @Autowired
    public Menu(ConsoleView view,
                PlayerService players,
                @Qualifier("MenuCommands") CommandSupplier supplier) {
        this.view = view;
        this.players = players;
        this.supplier = supplier;
    }

    public void run() {
        if(!loadPlayers()) return;
        view.displayMessage("Welcome to the Cryptograms Game!");
        view.displayMessage("What is your username?");
        player = getOrCreatePlayer(view.getInput()[0]);
        view.displayMessage("Hello " + player.getUsername() + "!");

    }

    private Player getOrCreatePlayer(String username) {
        try {
            return players.getPlayer(username);
        } catch (NoSuchElementException exception) {
            Player player = new Player(username);
            players.addPlayer(player);
            return player;
        }
    }

    public void mainLoop() {
        while (true) {
            view.displayMessage("Choose what you want to do next.");
            String[] input = view.getInput();
            if (input[0].contains("exit")) break;
            fetchAndExecuteCommand(input);
        }
    }

    private void fetchAndExecuteCommand(String[] input) {
        Command command = supplier.fetchCommand(input, player);
        command.execute();
    }

    public boolean loadPlayers() {
        try {
            players.loadPlayersFromFile();
        } catch (FileNotFoundException notFoundException) {
            view.displayMessage("Players file not found. Exiting...");
            return false;
        } catch (IOException e) {
            view.displayMessage("File format invalid. Exiting...");
            return false;
        }
        return true;
    }

}
