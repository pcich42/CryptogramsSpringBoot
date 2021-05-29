package com.example.cryptogramgamewithspring.Commands.Commands;


import com.example.cryptogramgamewithspring.Commands.Factories.CommandFactory;
import com.example.cryptogramgamewithspring.Controllers.GameContext;
import com.example.cryptogramgamewithspring.Controllers.GameplayController;
import com.example.cryptogramgamewithspring.Controllers.MenuContext;
import com.example.cryptogramgamewithspring.Cryptogram.Cryptogram;
import com.example.cryptogramgamewithspring.Cryptogram.CryptogramRepository;
import com.example.cryptogramgamewithspring.Player.Player;
import com.example.cryptogramgamewithspring.Player.PlayerService;
import com.example.cryptogramgamewithspring.Presentation.ConsoleView;
import com.example.cryptogramgamewithspring.Presentation.InputPrompt;

import java.io.IOException;
import java.util.Optional;

public class playGeneratedCryptogramCommand implements Command<MenuContext> {

    private final PlayerService playerService;
    private final ConsoleView view;
    private final InputPrompt prompt;
    private final CommandFactory<GameContext> gameplayCommands;
    private final String[] input;
    private final CryptogramRepository cryptogramRepository;
    private final Player player;

    public playGeneratedCryptogramCommand(MenuContext context) {
        this.playerService = context.getPlayers();
        this.view = context.getView();
        this.prompt = context.getPrompt();
        this.gameplayCommands = context.getCommandFactory();
        this.input = context.getInput();
        this.cryptogramRepository = context.getCryptogramRepository();
        this.player = context.getPlayer();
    }

    @Override
    public void execute() {
        Cryptogram cryptogram = getCryptogram();
        if (cryptogram == null) return;
        GameplayController game = new GameplayController(view, prompt, gameplayCommands, cryptogram, player);
        game.mainLoop();

    }

    private Cryptogram getCryptogram() {
        Cryptogram cryptogram;
        Optional<Cryptogram> optionalCryptogram = cryptogramRepository.generateCryptogram(input);
        if (optionalCryptogram.isEmpty()) {
            view.displayMessage("Couldn't retrieve Cryptogram");
            return null;
        }
        else cryptogram = optionalCryptogram.get();
        return cryptogram;
    }

    @Override
    public MenuContext getState() {
        return new MenuContext(prompt, view, playerService, gameplayCommands, input, cryptogramRepository, player);
    }

    @Override
    public boolean didExit() {
        return false;
    }
}
