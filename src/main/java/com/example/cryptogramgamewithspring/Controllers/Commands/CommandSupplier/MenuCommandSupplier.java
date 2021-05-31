package com.example.cryptogramgamewithspring.Controllers.Commands.CommandSupplier;

import com.example.cryptogramgamewithspring.Controllers.Commands.*;
import com.example.cryptogramgamewithspring.Controllers.GameplayController;
import com.example.cryptogramgamewithspring.Cryptogram.CryptogramRepository;
import com.example.cryptogramgamewithspring.Player.Player;
import com.example.cryptogramgamewithspring.Player.PlayerService;
import com.example.cryptogramgamewithspring.Presentation.ConsoleView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

@Component
@Qualifier("MenuCommands")
public class MenuCommandSupplier implements CommandSupplier {

    // a map of String to a function that takes a context and returns a menu command
    private final Map<String, BiFunction<String[], Player, Command>> commands;

    @Autowired
    public MenuCommandSupplier(ConsoleView view, GameplayController gameplayController, CryptogramRepository repository, PlayerService playerService) {
        this.commands = new HashMap<>();
        commands.put("new", (input, player) -> new playGeneratedCryptogramCommand(gameplayController, view, repository, player, input));
        commands.put("load", (input, player) -> new playLoadedCryptogramCommand(gameplayController, view, repository, player));
        commands.put("scores", (input, player) -> new showScoreboardCommand(view, playerService));
    }

    @Override
    public Command fetchCommand(String[] input, Player player) {
        return commands.getOrDefault(input[0], CommandNotFoundCommand::new).apply(input, player);
    }

}
