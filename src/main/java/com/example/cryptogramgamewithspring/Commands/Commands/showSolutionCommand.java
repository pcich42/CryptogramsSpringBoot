package com.example.cryptogramgamewithspring.Commands.Commands;

import com.example.cryptogramgamewithspring.Controllers.GameContext;

public class showSolutionCommand implements Command<GameContext> {

    public showSolutionCommand(GameContext context) {
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
