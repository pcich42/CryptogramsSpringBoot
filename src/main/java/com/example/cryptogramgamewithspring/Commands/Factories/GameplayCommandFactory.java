package com.example.cryptogramgamewithspring.Commands.Factories;

import com.example.cryptogramgamewithspring.Commands.Commands.*;
import com.example.cryptogramgamewithspring.Commands.Commands.Command;
import com.example.cryptogramgamewithspring.Controllers.GameContext;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Repository
@Qualifier("GamePlayCommands")
public class GameplayCommandFactory implements CommandFactory<Command<GameContext>> {

    private final Map<String, Supplier<Command<GameContext>>> commands;

    public GameplayCommandFactory() {
        this.commands = new HashMap<>();
        commands.put("undo", undoCommand::new);
        commands.put("solution", showSolutionCommand::new);
        commands.put("freq", showFrequenciesCommand::new);
        commands.put("exit", exitCommand::new);
        commands.put("hint", hintCommand::new);
        commands.put("save", saveGameCommand::new);
        commands.put("help", displayHelpCommand::new);
    }

    @Override
    public Command<GameContext> fetchCommand(String[] input) {
        return commands.getOrDefault(input[0], getDefaultCommandSupplier(input)).get();
    }

    private Supplier<Command<GameContext>> getDefaultCommandSupplier(String[] input) {
        return enterGuessCommand::new;
    }
}
