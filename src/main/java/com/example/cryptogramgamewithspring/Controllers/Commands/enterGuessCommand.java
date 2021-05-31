package com.example.cryptogramgamewithspring.Controllers.Commands;

import com.example.cryptogramgamewithspring.Controllers.Commands.CommandSupplier.GameContext;


public class enterGuessCommand implements Command {

    public enterGuessCommand(GameContext context) {

    }

    @Override
    public void execute() {

    }

    @Override
    public boolean didExit() {
        return false;
    }
}
