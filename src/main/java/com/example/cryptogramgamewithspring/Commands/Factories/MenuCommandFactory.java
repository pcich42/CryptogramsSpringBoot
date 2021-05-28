package com.example.cryptogramgamewithspring.Commands.Factories;

import com.example.cryptogramgamewithspring.Commands.Commands.Command;
import com.example.cryptogramgamewithspring.Commands.Commands.playGeneratedCryptogramCommand;
import com.example.cryptogramgamewithspring.Commands.Commands.playLoadedCryptogramCommand;
import com.example.cryptogramgamewithspring.Commands.Commands.showScoreboardCommand;
import com.example.cryptogramgamewithspring.Controllers.MenuContext;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Repository
@Qualifier("MenuCommands")
public class MenuCommandFactory implements CommandFactory<MenuContext> {

    // a map of String to a function that takes a context and returns a menu command
    private final Map<String, Function<MenuContext, Command<MenuContext>>> commands;

    public MenuCommandFactory() {
        commands = new HashMap<>();
        commands.put("new", playGeneratedCryptogramCommand::new);
        commands.put("load", playLoadedCryptogramCommand::new);
        commands.put("scores", showScoreboardCommand::new);
    }

    @Override
    public Command<MenuContext> fetchCommand(MenuContext context) {
        return commands.getOrDefault(context.getInput()[0], DefaultMenuCommand::new).apply(context);
    }

    private static class DefaultMenuCommand implements Command<MenuContext> {

        private DefaultMenuCommand(MenuContext context) {
        }

        @Override
        public void execute() {
            System.out.println("Command unavailable");
        }

        @Override
        public MenuContext getState() {
            return null;
        }

        @Override
        public boolean didExit() {
            return false;
        }
    }
}
