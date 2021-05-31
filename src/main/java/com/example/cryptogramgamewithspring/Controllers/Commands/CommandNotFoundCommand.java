package com.example.cryptogramgamewithspring.Controllers.Commands;

import com.example.cryptogramgamewithspring.Player.Player;

public class CommandNotFoundCommand implements Command {

    public CommandNotFoundCommand(String[] input, Player player) {
    }

    @Override
    public void execute() {

    }

    @Override
    public boolean didExit() {
        return false;
    }
}
