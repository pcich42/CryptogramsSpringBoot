package com.example.cryptogramgamewithspring.Controllers.Commands.CommandSupplier;

import com.example.cryptogramgamewithspring.Cryptogram.Cryptogram;
import com.example.cryptogramgamewithspring.Player.Player;

public class GameContext extends Context {
    private final String[] input;


    private final Cryptogram cryptogram;
    private final Player player;

    public GameContext(Cryptogram cryptogram, Player player, String[] input) {
        this.cryptogram = cryptogram;
        this.player = player;
        this.input = input;
    }

    public Cryptogram getCryptogram() {
        return cryptogram;
    }

    public Player getPlayer() {
        return player;
    }

    public String[] getInput() {
        return this.input;
    }
}
