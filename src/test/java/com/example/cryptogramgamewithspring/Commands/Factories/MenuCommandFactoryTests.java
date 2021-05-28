package com.example.cryptogramgamewithspring.Commands.Factories;

import com.example.cryptogramgamewithspring.Commands.Commands.Command;
import com.example.cryptogramgamewithspring.Commands.Commands.playGeneratedCryptogramCommand;
import com.example.cryptogramgamewithspring.Commands.Commands.playLoadedCryptogramCommand;
import com.example.cryptogramgamewithspring.Commands.Commands.showScoreboardCommand;
import com.example.cryptogramgamewithspring.Controllers.MenuContext;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MenuCommandFactoryTests {

    private final MenuCommandFactory factory = new MenuCommandFactory();

    @Test
    void input_new_returnsNewGameplayCommand() {
        String[] input = {"new"};
        Command<MenuContext> command = factory.fetchCommand(input);
        assertTrue(command instanceof playGeneratedCryptogramCommand);
    }

    @Test
    void input_load_returnsNewGameplayCommand() {
        String[] input = {"load"};
        Command<MenuContext> command = factory.fetchCommand(input);
        assertTrue(command instanceof playLoadedCryptogramCommand);
    }

    @Test
    void input_scores_returnsNewGameplayCommand() {
        String[] input = {"scores"};
        Command<MenuContext> command = factory.fetchCommand(input);
        assertTrue(command instanceof showScoreboardCommand);
    }
}
