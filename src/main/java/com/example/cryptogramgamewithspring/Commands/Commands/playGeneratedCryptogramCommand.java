package com.example.cryptogramgamewithspring.Commands.Commands;


import com.example.cryptogramgamewithspring.Commands.Factories.CommandFactory;
import com.example.cryptogramgamewithspring.Controllers.GameContext;
import com.example.cryptogramgamewithspring.Controllers.MenuContext;
import com.example.cryptogramgamewithspring.Player.PlayerService;
import com.example.cryptogramgamewithspring.Presentation.ConsoleView;
import com.example.cryptogramgamewithspring.Presentation.InputPrompt;

import java.io.IOException;

public class playGeneratedCryptogramCommand implements Command<MenuContext> {

    private final PlayerService playerService;
    private final ConsoleView view;
    private final InputPrompt prompt;
    private final CommandFactory<GameContext> gameplayCommands;
    private final String[] input;

    public playGeneratedCryptogramCommand(MenuContext context) {
        this.playerService = context.getPlayers();
        this.view = context.getView();
        this.prompt = context.getPrompt();
        this.gameplayCommands = context.getCommandFactory();
        this.input = context.getInput();
    }

    @Override
    public void execute() {



        try {
            playerService.savePlayers();
        } catch (IOException ioException) {
            view.displayMessage("Could not save players");
        }

    }

    @Override
    public MenuContext getState() {
        return new MenuContext(prompt, view, playerService, gameplayCommands, input);
    }

    @Override
    public boolean didExit() {
        return false;
    }
}
