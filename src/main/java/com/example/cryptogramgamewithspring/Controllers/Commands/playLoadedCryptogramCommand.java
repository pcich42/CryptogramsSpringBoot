package com.example.cryptogramgamewithspring.Controllers.Commands;

import com.example.cryptogramgamewithspring.Controllers.GameplayController;
import com.example.cryptogramgamewithspring.Cryptogram.Cryptogram;
import com.example.cryptogramgamewithspring.Cryptogram.CryptogramRepository;
import com.example.cryptogramgamewithspring.Player.InvalidFileFormatException;
import com.example.cryptogramgamewithspring.Player.Player;
import com.example.cryptogramgamewithspring.Presentation.ConsoleView;

import java.io.IOException;
import java.util.Optional;

public class playLoadedCryptogramCommand implements Command {

    private final GameplayController game;
    private final ConsoleView view;
    private final CryptogramRepository cryptogramRepository;
    private final Player player;

    public playLoadedCryptogramCommand(GameplayController game, ConsoleView view, CryptogramRepository cryptogramRepository, Player player) {
        this.game = game;
        this.view = view;
        this.cryptogramRepository = cryptogramRepository;
        this.player = player;
    }

    @Override
    public void execute() {
        try {
            Optional<Cryptogram> cryptogram = cryptogramRepository.loadCryptogram(player.getUsername());
            cryptogram.ifPresentOrElse(this::present, this::empty);
        } catch (InvalidFileFormatException invalidFileFormatException) {
            view.displayMessage("File is corrupted. Either remove the file manually or play and save a new game to be able to load.");
        } catch (IOException ioException) {
            view.displayMessage("Unknown problem occurred.");
        }
    }

    private void empty() {
        view.displayMessage("No cryptogram stored, Try creating a new one.");
    }

    private void present(Cryptogram cryptogram) {
        game.setCryptogram(cryptogram);
        game.setPlayer(player);
        game.mainLoop();
    }

    @Override
    public boolean didExit() {
        return false;
    }
}
