package com.example.cryptogramgamewithspring.Controllers.Commands;

import com.example.cryptogramgamewithspring.Controllers.GameplayController;
import com.example.cryptogramgamewithspring.Cryptogram.Cryptogram;
import com.example.cryptogramgamewithspring.Cryptogram.CryptogramRepository;
import com.example.cryptogramgamewithspring.Player.Player;
import com.example.cryptogramgamewithspring.Presentation.ConsoleView;

import java.io.IOException;
import java.util.Optional;

public class playGeneratedCryptogramCommand implements Command {

    private final GameplayController game;
    private final ConsoleView view;
    private final CryptogramRepository cryptogramRepository;
    private final Player player;
    private final String[] input;

    public playGeneratedCryptogramCommand(GameplayController game, ConsoleView view, CryptogramRepository cryptogramRepository, Player player, String[] input) {
        this.game = game;
        this.view = view;
        this.cryptogramRepository = cryptogramRepository;
        this.player = player;
        this.input = input;
    }

    @Override
    public void execute() {
        try {
            String type = getType(input);
            Optional<Cryptogram> optionalCryptogram = cryptogramRepository.generateCryptogram(type);
            optionalCryptogram.ifPresentOrElse(this::present, this::empty);
        } catch (IOException ioException) {
            view.displayMessage("Couldn't generate Cryptogram.");
        }
    }

    private String getType(String[] input) {
        try {
            String type = input[1];
            if (typeIsValid(type)) return type;
            else return "invalid";
        } catch (IndexOutOfBoundsException e) {
            return "letters";
        }
    }

    private boolean typeIsValid(String type) {
        return type.equals("letters") || type.equals("numbers");
    }

    @Override
    public boolean didExit() {
        return false;
    }

    private void present(Cryptogram cryptogram) {
        game.setPlayer(player);
        game.setCryptogram(cryptogram);
        game.mainLoop();
    }

    private void empty() {
        view.displayMessage("Unexpected cryptogram type. Try letters or numbers.");
    }


}
