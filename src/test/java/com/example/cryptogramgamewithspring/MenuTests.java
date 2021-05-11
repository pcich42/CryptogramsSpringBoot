package com.example.cryptogramgamewithspring;

import com.example.cryptogramgamewithspring.InputOutput.*;
import com.example.cryptogramgamewithspring.Interfaces.CommandFactory;
import com.example.cryptogramgamewithspring.Interfaces.MenuCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;

import com.example.cryptogramgamewithspring.Controllers.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static uk.org.webcompere.systemstubs.SystemStubs.*;


@ExtendWith(MockitoExtension.class)
public class MenuTests {

    @Mock
    private InputPrompt mockPrompt;
    @Mock
    private View mockView;
    @Mock
    private PlayerRepository mockPlayers;
    @Mock
    private CommandFactory<MenuCommand> mockCommandFactory;

    private Menu menu;

    @BeforeEach
    public void setUp() {
        menu = new Menu(mockPrompt, mockView, mockPlayers, mockCommandFactory);
    }

    @Test
    void MenuStartsWithMessage() throws Exception {
        String output = tapSystemOut(menu::run);
        assertEquals(output.trim(), "Welcome to the Cryptograms Game!");
    }

    @Test
    void givenMenuIsRunning_UserInputsExit_GameQuits() throws Exception {
        String[] inputExit = {"exit"};
        given(mockPrompt.getInput())
        .willReturn(inputExit);

        withTextFromSystemIn(inputExit[0])
                .execute(() -> {
                    String output = tapSystemOut(menu::mainLoop);
                    assertEquals(output, "Choose what you want to do next.\n");
                });
    }

    @Test
    void givenMenuIsRunning_WhenUserInputsACommandWithArguments_CommandIsExecuted() throws Exception {
        String[] input = {"command", "argument"};
        String[] exitInput = {"exit"};
        given(mockPrompt.getInput())
                .willReturn(input)
                .willReturn(exitInput);

        given(mockCommandFactory.fetchCommand(ArgumentMatchers.any()))
                .willReturn(() -> Arrays.stream(input).forEach(System.out::println));

        withTextFromSystemIn(input[0] + " " + input[1], exitInput[0])
                .execute(() -> {
                    String output = tapSystemOut(menu::mainLoop);
                    assertEquals(
                            "Choose what you want to do next.\n" +
                            "command\n" +
                            "argument\n" +
                            "Choose what you want to do next.\n", output);
                });
    }

    @Test
    void givenMenuIsRunning_WhenUserInputsMultipleCommandsWithArguments_CommandsAreExecuted() throws Exception {
        String[] inputOne = {"one", "argument_one"};
        String[] inputTwo = {"two", "argument_two"};
        String[] inputThree = {"three"};
        String[] exitInput = {"exit"};
        given(mockPrompt.getInput())
                .willReturn(inputOne)
                .willReturn(inputTwo)
                .willReturn(inputThree)
                .willReturn(exitInput);

        given(mockCommandFactory.fetchCommand(ArgumentMatchers.any()))
                .willReturn(() -> System.out.println("one argument_one"))
                .willReturn(() -> System.out.println("command two performed successfully."))
                .willReturn(() -> System.out.println("What? You egg?"));

        withTextFromSystemIn(
                String.join(" ", inputOne),
                String.join(" ", inputTwo),
                inputThree[0],
                exitInput[0])
                .execute(() -> {
                    String output = tapSystemOut(menu::mainLoop);
                    assertEquals(
                            "Choose what you want to do next.\n" +
                                    "one argument_one\n" +
                                    "Choose what you want to do next.\n" +
                                    "command two performed successfully.\n" +
                                    "Choose what you want to do next.\n" +
                                    "What? You egg?\n" +
                                    "Choose what you want to do next.\n", output);
                });
    }

    @Test
    void givenMenuIsRunning_whenPlayerFileIsLoadedProperly_loadPlayersReturnsTrue() throws IOException {
        willDoNothing()
                .given(mockPlayers).loadPlayersFromFile();
        assertTrue(menu.loadPlayers());
    }

    @Test
    void givenMenuIsRunning_whenPlayerFileIsNotLoadedProperly_loadPlayersReturnsFalse() throws IOException {
        willThrow(new FileNotFoundException())
                .given(mockPlayers).loadPlayersFromFile();
        assertFalse(menu.loadPlayers());
    }

}
