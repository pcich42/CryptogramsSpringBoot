package com.example.cryptogramgamewithspring.Commands.Commands;

import com.example.cryptogramgamewithspring.Controllers.GameContext;

public class hintCommand implements Command<GameContext> {

    public hintCommand(GameContext context) {
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
