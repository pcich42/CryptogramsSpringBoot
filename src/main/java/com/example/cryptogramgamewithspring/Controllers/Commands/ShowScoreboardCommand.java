package com.example.cryptogramgamewithspring.Controllers.Commands;

import com.example.cryptogramgamewithspring.Player.Player;
import com.example.cryptogramgamewithspring.Player.PlayerService;
import com.example.cryptogramgamewithspring.Presentation.ConsoleView;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class ShowScoreboardCommand implements Command{

    private static final int NO_PLAYERS_TO_SHOW = 10;
    private final ConsoleView view;
    private final PlayerService playerService;

    public ShowScoreboardCommand(ConsoleView view, PlayerService playerService) {
        this.view = view;
        this.playerService = playerService;
    }

    @Override
    public void execute() {
        Map<Player, Double> accuracies = playerService.getAccuracies();
        Optional<String> formattedAccuracies = getFormattedAccuracies(accuracies);
        formattedAccuracies.ifPresentOrElse(this::present, this::empty);
    }

    private Optional<String> getFormattedAccuracies(Map<Player, Double> accuracies) {
        AtomicInteger counter = new AtomicInteger(1);
        return accuracies.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                .map((entry) -> getTopTenPlayers(counter, entry))
                .reduce((acc, next) -> acc + next);
    }

    private String getTopTenPlayers(AtomicInteger counter, Map.Entry<Player, Double> entry) {
        int index = counter.getAndIncrement();
        if (index <= NO_PLAYERS_TO_SHOW) return index +
                ". " + entry.getKey().getUsername() +
                ": " + entry.getValue().toString() +
                "\n";
        else return "";
    }

    private void empty() {
        view.displayMessage("No players found :( sorry");
    }

    private void present(String s) {
        view.displayMessage("Top ten players by accuracy are:\n" + s);
    }

    @Override
    public boolean didExit() {
        return false;
    }
}
