package com.example.cryptogramgamewithspring.Commands.Factories;

import com.example.cryptogramgamewithspring.Commands.Commands.*;
import com.example.cryptogramgamewithspring.Commands.Commands.Command;
import com.example.cryptogramgamewithspring.Controllers.GameContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class GameCommandFactoryTests {

    @Mock
    private GameContext context;

    private final GameplayCommandFactory factory = new GameplayCommandFactory();

    private Command<GameContext> getCommand(GameContext context) {
        return factory.fetchCommand(context);
    }

    @Test
    void input_new_returnsNewGameplayCommand() {
        String[] input = {"p o"};
        given(context.getInput()).willReturn(input);
        Command<GameContext> command = getCommand(context);
        assertTrue(command instanceof enterGuessCommand);
    }

    @Test
    void input_undo_returnsNewGameplayCommand() {
        String[] input = {"undo"};
        given(context.getInput()).willReturn(input);
        Command<GameContext> command = getCommand(context);
        assertTrue(command instanceof undoCommand);
    }

    @Test
    void input_solution_returnsNewGameplayCommand() {
        String[] input = {"solution"};
        given(context.getInput()).willReturn(input);
        Command<GameContext> command = getCommand(context);
        assertTrue(command instanceof showSolutionCommand);
    }

    @Test
    void input_freq_returnsNewGameplayCommand() {
        String[] input = {"freq"};
        given(context.getInput()).willReturn(input);
        Command<GameContext> command = getCommand(context);
        assertTrue(command instanceof showFrequenciesCommand);
    }

    @Test
    void input_exit_returnsNewGameplayCommand() {
        String[] input = {"exit"};
        given(context.getInput()).willReturn(input);
        Command<GameContext> command = getCommand(context);
        assertTrue(command instanceof exitCommand);
    }

    @Test
    void input_hint_returnsNewGameplayCommand() {
        String[] input = {"hint"};
        given(context.getInput()).willReturn(input);
        Command<GameContext> command = getCommand(context);
        assertTrue(command instanceof hintCommand);
    }

    @Test
    void input_save_returnsNewGameplayCommand() {
        String[] input = {"save"};
        given(context.getInput()).willReturn(input);
        Command<GameContext> command = getCommand(context);
        assertTrue(command instanceof saveGameCommand);
    }

    @Test
    void input_help_returnsNewGameplayCommand() {
        String[] input = {"help"};
        given(context.getInput()).willReturn(input);
        Command<GameContext> command = getCommand(context);
        assertTrue(command instanceof displayHelpCommand);
    }
}
