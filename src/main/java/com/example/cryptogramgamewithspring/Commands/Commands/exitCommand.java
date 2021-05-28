package com.example.cryptogramgamewithspring.Commands.Commands;

import com.example.cryptogramgamewithspring.Controllers.GameContext;

public class exitCommand implements Command<GameContext> {
    public exitCommand(GameContext context) {
    }

    @Override
    public void execute() {

    }

    @Override
    public GameContext getState() {
        return null;
    }

    @Override
    public boolean didExit() {
        return false;
    }
}
