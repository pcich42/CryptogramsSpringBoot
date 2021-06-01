package com.example.cryptogramgamewithspring.Controllers.Commands;

public class UndoCommand implements Command {
    public UndoCommand() {
    }

    @Override
    public void execute() {
    }

    @Override
    public boolean didExit() {
        return false;
    }
}
