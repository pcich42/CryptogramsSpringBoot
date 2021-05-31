package com.example.cryptogramgamewithspring.Controllers;

import com.example.cryptogramgamewithspring.Controllers.Commands.*;
import com.example.cryptogramgamewithspring.Controllers.Commands.CommandSupplier.CommandSupplier;
import com.example.cryptogramgamewithspring.Controllers.Commands.CommandSupplier.GameContext;
import com.example.cryptogramgamewithspring.Cryptogram.Cryptogram;
import com.example.cryptogramgamewithspring.Player.Player;
import com.example.cryptogramgamewithspring.Presentation.ConsoleView;

public class GameplayController extends ConsoleCommandController {

    private final CommandSupplier<GameContext> supplier;
    private final ConsoleView view;
    private final Player player;
    private final Cryptogram cryptogram;

    public GameplayController(CommandSupplier<GameContext> supplier, ConsoleView view, Cryptogram cryptogram, Player player) {
        this.supplier = supplier;
        this.view = view;
        this.cryptogram = cryptogram;
        this.player = player;
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
        GameContext context = new GameContext(cryptogram, player, input);
        Command command = supplier.fetchCommand(input[0], context);
        command.execute();
        return command.didExit();
    }

    @Override
    public void run() {

    }
}
