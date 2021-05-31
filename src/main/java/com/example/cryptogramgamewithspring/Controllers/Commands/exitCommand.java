package com.example.cryptogramgamewithspring.Controllers.Commands;

public class exitCommand implements Command {
    public exitCommand() {
    }

    @Override
    public void execute() {

    }

    @Override
    public boolean didExit() {
        return false;
    }
}
