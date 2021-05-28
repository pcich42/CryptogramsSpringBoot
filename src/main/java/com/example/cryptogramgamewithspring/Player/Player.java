package com.example.cryptogramgamewithspring.Player;

import java.util.Objects;

public class Player {

    private final String username;

    public Player(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public Double getAccuracy() {
        return 1.0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return username.equals(player.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
