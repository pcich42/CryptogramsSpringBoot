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

    public void loadPlayersFromFile() throws IOException {
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

    public Map<Player, Double> getAccuracies() {
        Map<Player, Double> accuracies = new HashMap<>();
        players.forEach((player) -> accuracies.put(player, player.getAccuracy()));
        return accuracies;
    }

    public void savePlayers() throws IOException {
        repository.savePlayers(players);
    }
}
