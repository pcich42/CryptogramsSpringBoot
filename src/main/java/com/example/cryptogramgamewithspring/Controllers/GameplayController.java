package com.example.cryptogramgamewithspring.Controllers;

import com.example.cryptogramgamewithspring.Controllers.Commands.*;
import com.example.cryptogramgamewithspring.Controllers.Commands.CommandSupplier.CommandSupplier;
import com.example.cryptogramgamewithspring.Cryptogram.Cryptogram;
import com.example.cryptogramgamewithspring.Player.Player;
import com.example.cryptogramgamewithspring.Player.PlayerService;
import com.example.cryptogramgamewithspring.Presentation.ConsoleView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class GameplayController extends ConsoleCommandController {

    private final ConsoleView view;
    private final PlayerService playerService;
    private final CommandSupplier supplier;
    private Player player;
    private Cryptogram cryptogram;

    @Autowired
    public GameplayController(ConsoleView view, PlayerService playerService, @Qualifier("GameplayCommands") CommandSupplier supplier) {
        this.view = view;
        this.playerService = playerService;
        this.supplier = supplier;
    }

    public void mainLoop() {
        view.displayHelp();
        boolean isExitRequested = false;
        while (!isExitRequested) {

//            view.displayCryptogram(cryptogram);

            view.displayMessage("Type help to list all available commands.");
            view.displayMessage("Please enter a mapping in format <letter><space><cryptogram value>:");
            String[] input = view.getInput();
            isExitRequested = input[0].contains("exit") || executeCommand(input);
        }
    }

    private boolean executeCommand(String[] input) {
        Command command = supplier.fetchCommand(input, player);
        command.execute();
        return command.didExit();
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setCryptogram(Cryptogram cryptogram) {
        this.cryptogram = cryptogram;
    }

    @Override
    public void run() {

    }
}
