package com.example.cryptogramgamewithspring.Controllers.Commands.CommandSupplier;

import com.example.cryptogramgamewithspring.Controllers.Commands.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class GameplayCommandSupplier implements CommandSupplier<GameContext> {

    private final Map<String, Function<GameContext, Command>> commands;

    public GameplayCommandSupplier() {
        this.commands = new HashMap<>();
        commands.put("undo", input -> new undoCommand());
        commands.put("save", input -> new saveGameCommand());
        commands.put("solution", input -> new showSolutionCommand());
//        commands.put("scores", input -> new showScoreboardCommand());
        commands.put("freq", input -> new showFrequenciesCommand());
        commands.put("hint", input -> new hintCommand());
        commands.put("help", input -> new displayHelpCommand());
        commands.put("exit", input -> new exitCommand());
    }

    @Override
    public Command fetchCommand(String command, GameContext context) {
        return commands.getOrDefault(command, enterGuessCommand::new).apply(context);
    }
}
