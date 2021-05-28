package com.example.cryptogramgamewithspring.Commands.Commands;

public interface Command<T> {

    void execute();

    T getState();

    boolean didExit();

}
