package com.example.cryptogramgamewithspring.Controllers.Commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class exitCommandTest {

    @Test
    void exitCommandFinishesGame()
    {
        ExitCommand command = new ExitCommand();
        assertTrue(command.didExit());
    }

}