package com.example.cryptogramgamewithspring.Controllers.Commands;

public interface Command {

    void execute();

    boolean didExit();

}
