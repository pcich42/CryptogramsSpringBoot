package com.example.cryptogramgamewithspring.Commands.Factories;

import com.example.cryptogramgamewithspring.Commands.Commands.*;
import com.example.cryptogramgamewithspring.Commands.Commands.Command;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameCommandsTests {

    private final GameplayCommandFactory factory = new GameplayCommandFactory();

    @Test
    void input_new_returnsNewGameplayCommand() {
        String[] input = {"p o"};
        Command command = factory.fetchCommand(input);
        assertTrue(command instanceof enterGuessCommand);
    }

    @Test
    void input_undo_returnsNewGameplayCommand() {
        String[] input = {"undo"};
        Command command = factory.fetchCommand(input);
        assertTrue(command instanceof undoCommand);
    }

    @Test
    void input_solution_returnsNewGameplayCommand() {
        String[] input = {"solution"};
        Command command = factory.fetchCommand(input);
        assertTrue(command instanceof showSolutionCommand);
    }

    @Test
    void input_freq_returnsNewGameplayCommand() {
        String[] input = {"freq"};
        Command command = factory.fetchCommand(input);
        assertTrue(command instanceof showFrequenciesCommand);
    }

    @Test
    void input_exit_returnsNewGameplayCommand() {
        String[] input = {"exit"};
        Command command = factory.fetchCommand(input);
        assertTrue(command instanceof exitCommand);
    }

    @Test
    void input_hint_returnsNewGameplayCommand() {
        String[] input = {"hint"};
        Command command = factory.fetchCommand(input);
        assertTrue(command instanceof hintCommand);
    }

    @Test
    void input_save_returnsNewGameplayCommand() {
        String[] input = {"save"};
        Command command = factory.fetchCommand(input);
        assertTrue(command instanceof saveGameCommand);
    }

    @Test
    void input_help_returnsNewGameplayCommand() {
        String[] input = {"help"};
        Command command = factory.fetchCommand(input);
        assertTrue(command instanceof displayHelpCommand);
    }
}
