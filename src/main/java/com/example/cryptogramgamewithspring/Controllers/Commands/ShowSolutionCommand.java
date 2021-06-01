package com.example.cryptogramgamewithspring.Controllers.Commands;

import com.example.cryptogramgamewithspring.Controllers.Commands.CommandSupplier.GameContext;
import com.example.cryptogramgamewithspring.Cryptogram.Cryptogram;
import com.example.cryptogramgamewithspring.Presentation.ConsoleView;

public class ShowSolutionCommand implements Command {

    private final ConsoleView view;
    private final Cryptogram cryptogram;

    public ShowSolutionCommand(ConsoleView view, GameContext context) {
        this.view = view;
        this.cryptogram = context.getCryptogram();
    }

    @Override
    public void execute() {
        cryptogram.fillSolution();
        view.displayMessage("The solution is:\n" + cryptogram.parseToString());
    }

    @Override
    public boolean didExit() {
        return true;
    }

}
