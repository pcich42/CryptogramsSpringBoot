package com.example.cryptogramgamewithspring.Controllers;

import com.example.cryptogramgamewithspring.Commands.Factories.CommandFactory;
import com.example.cryptogramgamewithspring.Cryptogram.Cryptogram;
import com.example.cryptogramgamewithspring.Player.Player;
import com.example.cryptogramgamewithspring.Presentation.ConsoleView;
import com.example.cryptogramgamewithspring.Presentation.InputPrompt;

public class GameContext extends Context{

    private final Cryptogram cryptogram;
    private final Player player;
    private final ConsoleView view;
    private final InputPrompt prompt;
    private final CommandFactory<GameContext> commands;
    private final String[] input;

    public GameContext(Cryptogram cryptogram, Player player, ConsoleView view, InputPrompt prompt, CommandFactory<GameContext> commands, String[] input) {
        this.cryptogram = cryptogram;
        this.player = player;
        this.view = view;
        this.prompt = prompt;
        this.commands = commands;
        this.input = input;
    }

    public Cryptogram getCryptogram() {
        return cryptogram;
    }

    public Player getPlayer() {
        return player;
    }

    public ConsoleView getView() {
        return view;
    }

    public InputPrompt getPrompt() {
        return prompt;
    }

    public CommandFactory<GameContext> getCommands() {
        return commands;
    }

    public String[] getInput() {
        return input;
    }
}
