package com.example.cryptogramgamewithspring.Controllers.Commands.CommandSupplier;

import com.example.cryptogramgamewithspring.Player.Player;

public class MenuContext extends Context {

    private Player player;
    private String[] input;

    public MenuContext(Player player, String[] input) {
        this.player = player;
        this.input = input;
    }

    public Player getPlayer() {
        return this.player;
    }

    public String[] getInput() {
        return input;
    }
}
