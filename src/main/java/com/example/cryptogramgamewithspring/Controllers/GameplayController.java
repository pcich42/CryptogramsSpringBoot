package com.example.cryptogramgamewithspring.Controllers;

import com.example.cryptogramgamewithspring.Presentation.InputPrompt;
import com.example.cryptogramgamewithspring.Presentation.ConsoleView;
import com.example.cryptogramgamewithspring.Commands.Factories.CommandFactory;
import com.example.cryptogramgamewithspring.Commands.Commands.Command;

public class GameplayController implements GameController {

    private final ConsoleView view;
    private final InputPrompt prompt;
    private final CommandFactory<Command<GameContext>> commandFactory;

    public GameplayController(GameContext context) {
        this.view = context.getView();
        this.prompt = context.getPrompt();
        this.commandFactory = context.getCommands();
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
        Command<GameContext> command = commandFactory.fetchCommand(input);
        command.execute();
        setState(command.getState());
        return command.didExit();
    }

    @Override
    public void run() {

    }

    private void setState(GameContext context) {
    }
}
