package com.example.cryptogramgamewithspring.Controllers.Commands;

import com.example.cryptogramgamewithspring.Presentation.ConsoleView;

import java.util.List;

public class DisplayHelpCommand implements Command {
    private final List<String> commands;
    private final ConsoleView view;

    public DisplayHelpCommand(ConsoleView view, List<String> commands) {
        this.commands = commands;
        this.view = view;
    }

    @Override
    public void execute() {
        String displayString = "All available commands are:\n" + commands.stream().reduce((a, b) -> a+"\n"+b ).orElse("");
        view.displayMessage(displayString);
    }

    @Override
    public boolean didExit() {
        return false;
    }
}
