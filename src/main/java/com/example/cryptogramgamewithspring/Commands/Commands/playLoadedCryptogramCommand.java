package com.example.cryptogramgamewithspring.Commands.Commands;

import com.example.cryptogramgamewithspring.Controllers.MenuContext;

public class playLoadedCryptogramCommand implements Command<MenuContext> {

    @Override
    public void execute() {

    }

    @Override
    public MenuContext getState() {
        return null;
    }

    @Override
    public boolean didExit() {
        return false;
    }
}