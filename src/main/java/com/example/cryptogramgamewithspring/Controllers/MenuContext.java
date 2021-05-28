package com.example.cryptogramgamewithspring.Controllers;

import com.example.cryptogramgamewithspring.Commands.Factories.CommandFactory;
import com.example.cryptogramgamewithspring.Player.PlayerService;
import com.example.cryptogramgamewithspring.Presentation.ConsoleView;
import com.example.cryptogramgamewithspring.Presentation.InputPrompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Objects;

public class MenuContext {

    private final CommandFactory<GameContext> commandFactory;
    private final InputPrompt prompt;
    private final ConsoleView view;
    private final PlayerService players;
    private final String[] input;

    public MenuContext(InputPrompt prompt, ConsoleView view, PlayerService players, @Qualifier("GameplayCommands") CommandFactory<GameContext> commandFactory, String[] input) {
        this.prompt = prompt;
        this.view = view;
        this.players = players;
        this.commandFactory = commandFactory;
        this.input = input;
    }

    public InputPrompt getPrompt() {
        return prompt;
    }

    public ConsoleView getView() {
        return view;
    }

    public PlayerService getPlayers() {
        return players;
    }

    public CommandFactory<GameContext> getCommandFactory() {
        return commandFactory;
    }

    public String[] getInput() {
        return input;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuContext context = (MenuContext) o;
        return Objects.equals(commandFactory, context.commandFactory) && Objects.equals(prompt, context.prompt) && Objects.equals(view, context.view) && Objects.equals(players, context.players);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commandFactory, prompt, view, players);
    }
}
