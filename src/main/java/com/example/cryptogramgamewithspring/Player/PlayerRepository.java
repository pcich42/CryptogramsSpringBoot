package com.example.cryptogramgamewithspring.Player;

import java.io.IOException;
import java.util.Set;

public interface PlayerRepository {
    void savePlayers(Set<Player> players) throws IOException;
    Set<Player> getPlayers() throws IOException, InvalidFileFormatException;
}
