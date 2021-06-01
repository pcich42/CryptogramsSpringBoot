package com.example.cryptogramgamewithspring.Controllers.Commands;

public class ExitCommand implements Command {
    public ExitCommand() {
    }

    @Override
    public void execute() {

    }

    @Override
    public boolean didExit() {
        return true;
    }
}
