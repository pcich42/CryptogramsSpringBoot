package com.example.cryptogramgamewithspring.Controllers;

import com.example.cryptogramgamewithspring.Commands.Commands.Command;
import com.example.cryptogramgamewithspring.Cryptogram.CryptogramRepository;
import com.example.cryptogramgamewithspring.Player.InvalidFileFormatException;
import com.example.cryptogramgamewithspring.Player.Player;
import com.example.cryptogramgamewithspring.Presentation.*;
import com.example.cryptogramgamewithspring.Commands.Factories.CommandFactory;
import com.example.cryptogramgamewithspring.Player.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

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
    private ConsoleView mockView;
    @Mock
    private PlayerService mockPlayers;
    @Mock
    private CommandFactory<MenuContext> mockMenuCommandFactory;
    @Mock
    private CommandFactory<GameContext> mockGameCommandFactory;
    @Mock
    private CryptogramRepository cryptogramRepository;

    private Menu menu;

    @BeforeEach
    public void setUp() {
        menu = new Menu(mockPrompt, mockView, mockPlayers, mockMenuCommandFactory, mockGameCommandFactory, cryptogramRepository);
    }

    @Test
    void MenuStartsWithMessage() throws Exception {
        willDoNothing().given(mockPlayers).loadPlayersFromFile();
        given(mockPrompt.getInput()).willReturn(new String[]{"test_user"});
        given(mockPlayers.getPlayer(anyString())).willReturn(new Player("test_user"));
        willCallRealMethod().given(mockView).displayMessage(anyString());
        String output = tapSystemOut(menu::run);
        assertEquals(">>>>> Welcome to the Cryptograms Game!\n" +
                "\n" +
                ">>>>> What is your username?\n" +
                "\n" +
                ">>>>> Hello test_user!",
                output.trim());

    }

    @Test
    void givenMenuIsRunning_UserInputsExit_GameQuits() throws Exception {
        String[] inputExit = {"exit"};
        willCallRealMethod().given(mockView).displayMessage(anyString());
        given(mockPrompt.getInput())
                .willReturn(inputExit);

        withTextFromSystemIn(inputExit)
                .execute(() -> {
                    String output = tapSystemOut(menu::mainLoop);
                    assertEquals(">>>>> Choose what you want to do next.\n" +
                            "\n", output);
                });
    }

    @Test
    void givenMenuIsRunning_WhenUserInputsACommandWithArguments_CommandIsExecuted(@Mock Command<MenuContext> command1) throws Exception {
        String[] input = {"command", "argument"};
        String[] exitInput = {"exit"};
        willCallRealMethod().given(mockView).displayMessage(anyString());
        given(mockPrompt.getInput())
                .willReturn(input)
                .willReturn(exitInput);

        willAnswer((invocationOnMock -> {
            Arrays.stream(input).forEach(System.out::println);
            return null;
        })).given(command1).execute();

        given(mockMenuCommandFactory.fetchCommand(any()))
                .willReturn(command1);

        withTextFromSystemIn(input[0] + " " + input[1], exitInput[0])
                .execute(() -> {
                    String output = tapSystemOut(menu::mainLoop);
                    assertEquals(
                            ">>>>> Choose what you want to do next.\n" +
                                    "\n" +
                                    "command\n" +
                                    "argument\n" +
                                    ">>>>> Choose what you want to do next.\n" +
                                    "\n", output);
                });
    }

    @Test
    void givenMenuIsRunning_WhenUserInputsMultipleCommandsWithArguments_CommandsAreExecuted(@Mock Command<MenuContext> command1,
                                                                                            @Mock Command<MenuContext> command2,
                                                                                            @Mock Command<MenuContext> command3) throws Exception {
        String[] inputOne = {"one", "argument_one"};
        String[] inputTwo = {"two", "argument_two"};
        String[] inputThree = {"three"};
        String[] exitInput = {"exit"};
        given(mockPrompt.getInput())
                .willReturn(inputOne)
                .willReturn(inputTwo)
                .willReturn(inputThree)
                .willReturn(exitInput);

        willAnswer((invocationOnMock -> {
            System.out.println(inputOne[0] + " " + inputOne[1]);
            return null;
        })).given(command1).execute();

        will((invocationOnMock -> {
            System.out.println("command two performed successfully.");
            return null;
        })).given(command2).execute();

        will((invocationOnMock -> {
            System.out.println("What? You egg?");
            return null;
        })).given(command3).execute();

        given(mockMenuCommandFactory.fetchCommand(any()))
                .willReturn(command1)
                .willReturn(command2)
                .willReturn(command3);

        willCallRealMethod().given(mockView).displayMessage(anyString());

        withTextFromSystemIn(
                String.join(" ", inputOne),
                String.join(" ", inputTwo),
                inputThree[0],
                exitInput[0])
                .execute(() -> {
                    String output = tapSystemOut(menu::mainLoop);
                    assertEquals(
                            ">>>>> Choose what you want to do next.\n" +
                                    "\n" +
                                    "one argument_one\n" +
                                    ">>>>> Choose what you want to do next.\n" +
                                    "\n" +
                                    "command two performed successfully.\n" +
                                    ">>>>> Choose what you want to do next.\n" +
                                    "\n" +
                                    "What? You egg?\n" +
                                    ">>>>> Choose what you want to do next.\n" +
                                    "\n",
                            output);
                });
    }

    @Test
    void givenMenuIsRunning_whenPlayerFileIsLoadedProperly_loadPlayersReturnsTrue() throws IOException, InvalidFileFormatException {
        willDoNothing()
                .given(mockPlayers).loadPlayersFromFile();
        assertTrue(menu.loadPlayers());
    }

    @Test
    void givenMenuIsRunning_whenPlayerFileIsNotLoadedProperly_loadPlayersReturnsFalse() throws IOException, InvalidFileFormatException {
        willThrow(new FileNotFoundException())
                .given(mockPlayers).loadPlayersFromFile();
        assertFalse(menu.loadPlayers());
    }

}
