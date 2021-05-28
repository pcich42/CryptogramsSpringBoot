package com.example.cryptogramgamewithspring.Player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class PlayerService {

    private Set<Player> players;
    private final PlayerRepository repository;

    @Autowired
    public PlayerService(PlayerRepository repository) {
        this.repository = repository;
        this.players = new HashSet<>();
    }

    public void loadPlayersFromFile() throws InvalidFileFormatException, IOException {
        this.players = repository.getPlayers();
    }

    public Player getPlayer(String username) {
        return players.stream()
                .filter((player -> player.getUsername().equals(username)))
                .findAny().orElseThrow();
    }

    public boolean addPlayer(Player player) {
        return players.add(player);
    }

    public SortedMap<Double, Player> getAccuracies() {
        SortedMap<Double, Player> accuracies = new TreeMap<>();
        players.forEach((player) -> accuracies.put(player.getAccuracy(), player));
        return accuracies;
    }

    public void savePlayers() throws IOException {
        repository.savePlayers(players);
    }
}
