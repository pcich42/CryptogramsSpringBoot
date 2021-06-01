package com.example.cryptogramgamewithspring.Controllers.Commands;

import com.example.cryptogramgamewithspring.Controllers.Commands.CommandSupplier.GameContext;
import com.example.cryptogramgamewithspring.Cryptogram.Cryptogram;
import com.example.cryptogramgamewithspring.Player.Player;
import com.example.cryptogramgamewithspring.Presentation.ConsoleView;

public class EnterGuessCommand implements Command {

    private final Cryptogram cryptogram;
    private final Player player;
    private final ConsoleView view;
    private boolean exit;

    public EnterGuessCommand(ConsoleView view, GameContext context) {
        this.cryptogram = context.getCryptogram();
        this.player = context.getPlayer();
        this.view = view;
        this.exit = false;
    }

    @Override
    public void execute() {
        checkIfFinished();
    }

    private void checkIfFinished() {
        if (cryptogram.isFinished()) {
            if (cryptogram.isSolutionCorrect()) {
                view.displayMessage("Congratulations! You completed this cryptogram! We're now taking you back to menu!");
                exit = true;
            } else {
                view.displayMessage("Something seems off here... Try again.");
                exit = false;
            }
        }
    }

    @Override
    public boolean didExit() {
        return this.exit;
    }
}
