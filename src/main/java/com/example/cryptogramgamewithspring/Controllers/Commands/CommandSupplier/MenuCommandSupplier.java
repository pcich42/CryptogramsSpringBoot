package com.example.cryptogramgamewithspring.Controllers.Commands.CommandSupplier;

import com.example.cryptogramgamewithspring.Controllers.Commands.*;
import com.example.cryptogramgamewithspring.Cryptogram.CryptogramRepository;
import com.example.cryptogramgamewithspring.Player.PlayerService;
import com.example.cryptogramgamewithspring.Presentation.ConsoleView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class MenuCommandSupplier implements CommandSupplier<MenuContext> {

    // a map of String to a function that takes a context and returns a menu command
    private final Map<String, Function<MenuContext, Command>> commands;
    private final ConsoleView view;

    @Autowired
    public MenuCommandSupplier(ConsoleView view, CryptogramRepository repository, PlayerService playerService, CommandSupplier<GameContext> supplier) {
        this.view = view;
        this.commands = new HashMap<>();
        commands.put("new", (context) -> new PlayGeneratedCryptogramCommand(view, repository, context.getPlayer(), context.getInput(), supplier));
        commands.put("load", (context) -> new PlayLoadedCryptogramCommand(view, repository, context.getPlayer(), supplier));
        commands.put("scores", (context) -> new ShowScoreboardCommand(view, playerService));
    }

    @Override
    public Command fetchCommand(String command, MenuContext context) {
        return commands.getOrDefault(command, (ctx) -> new CommandNotFoundCommand(view)).apply(context);
    }

}
