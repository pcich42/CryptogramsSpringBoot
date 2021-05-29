package com.example.cryptogramgamewithspring.Controllers;

import com.example.cryptogramgamewithspring.Cryptogram.Cryptogram;
import com.example.cryptogramgamewithspring.Player.Player;
import com.example.cryptogramgamewithspring.Presentation.InputPrompt;
import com.example.cryptogramgamewithspring.Presentation.ConsoleView;
import com.example.cryptogramgamewithspring.Commands.Factories.CommandFactory;
import com.example.cryptogramgamewithspring.Commands.Commands.Command;

public class GameplayController implements GameController {

    private ConsoleView view;
    private InputPrompt prompt;
    private CommandFactory<GameContext> commandFactory;
    private Cryptogram cryptogram;
    private Player player;


    public GameplayController(ConsoleView view, InputPrompt prompt, CommandFactory<GameContext> commandFactory, Cryptogram cryptogram, Player player) {
        this.view = view;
        this.prompt = prompt;
        this.commandFactory = commandFactory;
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
            String[] input = prompt.getInput();
            isExitRequested = input[0].contains("exit") || executeCommand(input);
        }
    }

    private boolean executeCommand(String[] input) {
        GameContext context = new GameContext(cryptogram, player, view, prompt, commandFactory, input);
        Command<GameContext> command = commandFactory.fetchCommand(context);
        command.execute();
        setState(command.getState());
        return command.didExit();
    }

    @Override
    public void run() {

    }

    private void setState(GameContext context) {
//        this.cryptogram = context.getCryptogram();
//        this.player = context.getPlayer();
    }
}
