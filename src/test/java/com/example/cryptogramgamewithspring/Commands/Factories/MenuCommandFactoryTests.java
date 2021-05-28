package com.example.cryptogramgamewithspring.Commands.Factories;

import com.example.cryptogramgamewithspring.Commands.Commands.Command;
import com.example.cryptogramgamewithspring.Commands.Commands.playGeneratedCryptogramCommand;
import com.example.cryptogramgamewithspring.Commands.Commands.playLoadedCryptogramCommand;
import com.example.cryptogramgamewithspring.Commands.Commands.showScoreboardCommand;
import com.example.cryptogramgamewithspring.Controllers.GameContext;
import com.example.cryptogramgamewithspring.Controllers.Menu;
import com.example.cryptogramgamewithspring.Controllers.MenuContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class MenuCommandFactoryTests {

    @Mock
    private MenuContext context;

    private final MenuCommandFactory factory = new MenuCommandFactory();

    private Command<MenuContext> getCommand(MenuContext context) {
        return factory.fetchCommand(context);
    }

    @Test
    void input_new_returnsNewGameplayCommand() {
        String[] input = {"new"};
        given(context.getInput()).willReturn(input);
        Command<MenuContext> command = getCommand(context);
        assertTrue(command instanceof playGeneratedCryptogramCommand);
    }

    @Test
    void input_new_cryptogramType_returnsNewGameplayCommand() {
        String[] input = {"new", "letters"};
        given(context.getInput()).willReturn(input);
        Command<MenuContext> command = getCommand(context);
        assertTrue(command instanceof playGeneratedCryptogramCommand);
    }

    @Test
    void input_load_returnsNewGameplayCommand() {
        String[] input = {"load"};
        given(context.getInput()).willReturn(input);
        Command<MenuContext> command = getCommand(context);
        assertTrue(command instanceof playLoadedCryptogramCommand);
    }

    @Test
    void input_scores_returnsNewGameplayCommand() {
        String[] input = {"scores"};
        given(context.getInput()).willReturn(input);
        Command<MenuContext> command = getCommand(context);
        assertTrue(command instanceof showScoreboardCommand);
    }
}
