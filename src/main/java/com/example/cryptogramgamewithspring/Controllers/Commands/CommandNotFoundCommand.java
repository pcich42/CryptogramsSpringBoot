package com.example.cryptogramgamewithspring.Controllers.Commands;

import com.example.cryptogramgamewithspring.Presentation.ConsoleView;

public class CommandNotFoundCommand implements Command {

    public CommandNotFoundCommand(ConsoleView view) {
        view.displayMessage(">>>>> Command Not Found Type Help for List of Commands.");
    }

    @Override
    public void execute() {

    }

    @Override
    public boolean didExit() {
        return false;
    }
}
