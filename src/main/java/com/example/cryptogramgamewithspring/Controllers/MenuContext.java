package com.example.cryptogramgamewithspring.Controllers;

import com.example.cryptogramgamewithspring.Commands.Commands.Command;
import com.example.cryptogramgamewithspring.Commands.Factories.CommandFactory;
import com.example.cryptogramgamewithspring.Cryptogram.CryptogramRepository;
import com.example.cryptogramgamewithspring.Player.PlayerService;
import com.example.cryptogramgamewithspring.Presentation.ConsoleView;
import com.example.cryptogramgamewithspring.Presentation.InputPrompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class MenuContext {

    private final CryptogramRepository cryptogramRepository;
    private final InputPrompt prompt;
    private final ConsoleView view;
    private final PlayerService players;

    public InputPrompt getPrompt() {
        return prompt;
    }

    public ConsoleView getView() {
        return view;
    }

    public PlayerService getPlayers() {
        return players;
    }

    public CommandFactory<Command<MenuContext>> getCommandFactory() {
        return commandFactory;
    }

    private final CommandFactory<Command<MenuContext>> commandFactory;

    @Autowired
    public MenuContext(CryptogramRepository cryptogramRepository, InputPrompt prompt, ConsoleView view, PlayerService players, @Qualifier("MenuCommands") CommandFactory<Command<MenuContext>> commandFactory) {
        this.cryptogramRepository = cryptogramRepository;
        this.prompt = prompt;
        this.view = view;
        this.players = players;
        this.commandFactory = commandFactory;
    }

    public CryptogramRepository getCryptogramRepository() {
        return cryptogramRepository;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuContext that = (MenuContext) o;
        return Objects.equals(cryptogramRepository, that.cryptogramRepository);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cryptogramRepository);
    }

}
