package com.example.cryptogramgamewithspring.Controllers;

import com.example.cryptogramgamewithspring.Cryptogram.Cryptogram;
import com.example.cryptogramgamewithspring.Player.Player;
import com.example.cryptogramgamewithspring.Presentation.InputPrompt;
import com.example.cryptogramgamewithspring.Presentation.ConsoleView;
import com.example.cryptogramgamewithspring.Commands.Factories.CommandFactory;
import com.example.cryptogramgamewithspring.Commands.Commands.Command;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.*;
import static uk.org.webcompere.systemstubs.SystemStubs.tapSystemOut;
import static uk.org.webcompere.systemstubs.SystemStubs.withTextFromSystemIn;

@ExtendWith(MockitoExtension.class)
public class GameplayControllerTests {

    @Mock
    private ConsoleView mockView;
    @Mock
    private InputPrompt mockPrompt;
    @Mock
    private CommandFactory<Command<GameContext>> mockCommandFactory;
    @Mock
    private Cryptogram cryptogram;
    @Mock
    private Player player;

    private GameplayController gameplay;

    @BeforeEach
    void setUp() {
        GameContext context = new GameContext(cryptogram, player, mockView, mockPrompt, mockCommandFactory);
        gameplay = new GameplayController(context);
    }

    @Test
    void whenUserTypesInExit_GameExits() throws Exception {
        String[] inputExit = {"exit"};
        willCallRealMethod().given(mockView).displayMessage(anyString());

        given(mockPrompt.getInput())
                .willReturn(inputExit);

        withTextFromSystemIn(inputExit)
                .execute(() -> {
                    String output = tapSystemOut(gameplay::mainLoop);
                    assertEquals(">>>>> Type help to list all available commands.\n" +
                                    "\n" +
                                    ">>>>> Please enter a mapping in format <letter><space><cryptogram value>:\n" +
                                    "\n",
                            output);
                });


    }

    @Test
    void givenMenuIsRunning_WhenUserInputsACommandWithArguments_CommandIsExecuted(@Mock Command<GameContext> command1) throws Exception {
        String[] input = {"command", "argument"};
        String[] exitInput = {"exit"};
        willCallRealMethod().given(mockView).displayMessage(anyString());
        given(mockPrompt.getInput())
                .willReturn(input)
                .willReturn(exitInput);

        will((invocationOnMock -> {
            Arrays.stream(input).forEach(System.out::println);
            return null;
        })).given(command1).execute();

        given(mockCommandFactory.fetchCommand(any()))
                .willReturn(command1);

        withTextFromSystemIn(input[0] + " " + input[1], exitInput[0])
                .execute(() -> {
                    String output = tapSystemOut(gameplay::mainLoop);
                    assertEquals(
                            ">>>>> Type help to list all available commands.\n" +
                                    "\n" +
                                    ">>>>> Please enter a mapping in format <letter><space><cryptogram value>:\n" +
                                    "\n" +
                                    "command\n" +
                                    "argument\n" +
                                    ">>>>> Type help to list all available commands.\n" +
                                    "\n" +
                                    ">>>>> Please enter a mapping in format <letter><space><cryptogram value>:\n" +
                                    "\n",
                            output);
                });
    }

    @Test
    void WhenUserInputsMultipleCommandsWithArguments_CommandsAreExecuted(@Mock Command<GameContext> command1,
                                                                         @Mock Command<GameContext> command2,
                                                                         @Mock Command<GameContext> command3
    ) throws Exception {
        String[] inputOne = {"one", "argument_one"};
        String[] inputTwo = {"two", "argument_two"};
        String[] inputThree = {"three"};
        String[] exitInput = {"exit"};
        given(mockPrompt.getInput())
                .willReturn(inputOne)
                .willReturn(inputTwo)
                .willReturn(inputThree)
                .willReturn(exitInput);

        will((invocationOnMock -> {
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

        given(mockCommandFactory.fetchCommand(any()))
                .willReturn(command1)
                .willReturn(command2)
                .willReturn(command3);

        willDoNothing().given(mockView).displayMessage(anyString());

        withTextFromSystemIn(
                String.join(" ", inputOne),
                String.join(" ", inputTwo),
                inputThree[0],
                exitInput[0])
                .execute(() -> {
                    String output = tapSystemOut(gameplay::mainLoop);
                    assertEquals("one argument_one\n" +
                                    "command two performed successfully.\n" +
                                    "What? You egg?\n",
                                    output);
                });
    }

}