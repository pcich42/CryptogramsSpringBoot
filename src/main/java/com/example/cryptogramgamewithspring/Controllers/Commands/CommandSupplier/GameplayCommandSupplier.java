package com.example.cryptogramgamewithspring.Controllers.Commands.CommandSupplier;

import com.example.cryptogramgamewithspring.Controllers.Commands.*;
import com.example.cryptogramgamewithspring.Player.PlayerService;
import com.example.cryptogramgamewithspring.Presentation.ConsoleView;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;

@Component
public class GameplayCommandSupplier implements CommandSupplier<GameContext> {

    private final Map<String, Function<GameContext, Command>> commands;
    private final ConsoleView view;
    private final PlayerService playerService;
    private final List<String> descriptions;


    public GameplayCommandSupplier(ConsoleView view, PlayerService playerService) {
        this.view = view;
        this.playerService = playerService;
        this.descriptions = new ArrayList<>();
        this.commands = new HashMap<>();
        descriptions.add("<guess> <crypto value> - maps guess to the encrypted value");

        registerCommandWithArgs("undo",
                context -> new UndoCommand(),
                "removes a guess from solution", List.of("guess"));

        registerCommand("hint",
                context -> new HintCommand(),
                "provides a hint");

        registerCommand("solution",
                context -> new ShowSolutionCommand(view, context),
                "displays a solution, no points are awarded for this game");

        registerCommand("freq",
                context -> new ShowFrequenciesCommand(),
                "displays the relative frequencies of values of the cryptogram and frequencies of usage of letters in the English language");

        registerCommand("scores",
                context -> new ShowScoreboardCommand(this.view, this.playerService),
                "displays top ten players by accuracy");

        registerCommand("save",
                context -> new SaveGameCommand(),
                "saves this game");

        registerCommand("help",
                context -> new DisplayHelpCommand(view, descriptions),
                "displays this prompt");

        registerCommand("exit",
                context -> new ExitCommand(),
                "exits the game");
    }

    @Override
    public Command fetchCommand(String command, GameContext context) {
        return commands.getOrDefault(command, ctx -> new EnterGuessCommand(view, ctx)).apply(context);
    }

    private void registerCommand(String name, Function<GameContext, Command> commandConstructor, String description) {
        registerCommandWithArgs(name, commandConstructor, description, List.of());
    }

    private void registerCommandWithArgs(String name, Function<GameContext, Command> commandConstructor, String description, List<String> args) {
        commands.put(name, commandConstructor);
        String formattedArgs = args.stream()
                .map(a -> "<" + a + ">")
                .reduce((a, b) -> a + " " + b)
                .map(s -> " " + s)
                .orElse("");
        descriptions.add(name + formattedArgs + " - " + description);
    }
}
