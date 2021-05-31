package com.example.cryptogramgamewithspring.Controllers.Commands.CommandSupplier;

import com.example.cryptogramgamewithspring.Controllers.Commands.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class GameplayCommandSupplierTests {

    @Mock
    private GameContext context;

    private final GameplayCommandSupplier factory = new GameplayCommandSupplier();

    private Command getCommand(String[] input) {
//        given(context.getInput()).willReturn(input);
        return factory.fetchCommand(input[0], context);
    }

    @Test
    void input_new_returnsNewGameplayCommand() {
        String[] input = {"p o"};
        Command command = getCommand(input);
        assertTrue(command instanceof enterGuessCommand);
    }

    @Test
    void input_undo_returnsNewGameplayCommand() {
        String[] input = {"undo"};
        Command command = getCommand(input);
        assertTrue(command instanceof undoCommand);
    }

    @Test
    void input_solution_returnsNewGameplayCommand() {
        String[] input = {"solution"};
        Command command = getCommand(input);
        assertTrue(command instanceof showSolutionCommand);
    }

    @Test
    void input_freq_returnsNewGameplayCommand() {
        String[] input = {"freq"};
        Command command = getCommand(input);
        assertTrue(command instanceof showFrequenciesCommand);
    }

    @Test
    void input_exit_returnsNewGameplayCommand() {
        String[] input = {"exit"};
        Command command = getCommand(input);
        assertTrue(command instanceof exitCommand);
    }

    @Test
    void input_hint_returnsNewGameplayCommand() {
        String[] input = {"hint"};
        Command command = getCommand(input);
        assertTrue(command instanceof hintCommand);
    }

    @Test
    void input_save_returnsNewGameplayCommand() {
        String[] input = {"save"};
        Command command = getCommand(input);
        assertTrue(command instanceof saveGameCommand);
    }

    @Test
    void input_help_returnsNewGameplayCommand() {
        String[] input = {"help"};
        Command command = getCommand(input);
        assertTrue(command instanceof displayHelpCommand);
    }
}
