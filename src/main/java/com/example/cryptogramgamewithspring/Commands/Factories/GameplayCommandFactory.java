package com.example.cryptogramgamewithspring.Commands.Factories;

import com.example.cryptogramgamewithspring.Commands.Commands.*;
import com.example.cryptogramgamewithspring.Commands.Commands.Command;
import com.example.cryptogramgamewithspring.Controllers.GameContext;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Repository
@Qualifier("GameplayCommands")
public class GameplayCommandFactory implements CommandFactory<GameContext> {

    private final Map<String, Function<GameContext, Command<GameContext>>> commands;

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
    public Command<GameContext> fetchCommand(GameContext context) {
        String[] input = context.getInput();
        return commands.getOrDefault(input[0], getDefaultCommandSupplier(input)).apply(context);
    }

    private Function<GameContext, Command<GameContext>> getDefaultCommandSupplier(String[] input) {
        return enterGuessCommand::new;
    }
}
