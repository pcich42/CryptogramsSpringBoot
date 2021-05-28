package com.example.cryptogramgamewithspring.Commands.Commands;


import com.example.cryptogramgamewithspring.Controllers.MenuContext;

public class playGeneratedCryptogramCommand implements Command<MenuContext> {


    public playGeneratedCryptogramCommand() {
    }

    @Override
    public void execute() {

    }

    @Override
    public MenuContext getState() {
        return new MenuContext(null, null, null, null, null);
    }

    @Override
    public boolean didExit() {
        return false;
    }
}
