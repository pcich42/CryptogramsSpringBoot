package com.example.cryptogramgamewithspring.Controllers;

import com.example.cryptogramgamewithspring.Controllers.Commands.Command;
import com.example.cryptogramgamewithspring.Controllers.Commands.CommandSupplier.CommandSupplier;
import com.example.cryptogramgamewithspring.Player.PlayerService;
import com.example.cryptogramgamewithspring.Presentation.ConsoleView;
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
    private CommandSupplier commandSupplier;
    @Mock
    private PlayerService playerService;

    private GameplayController gameplay;

    @BeforeEach
    void setUp() {
        gameplay = new GameplayController(mockView, playerService, commandSupplier);
    }

    @Test
    void whenUserTypesInExit_GameExits() throws Exception {
        String[] inputExit = {"exit"};
        willCallRealMethod().given(mockView).displayMessage(anyString());

        given(mockView.getInput())
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
    void givenMenuIsRunning_WhenUserInputsACommandWithArguments_CommandIsExecuted(@Mock Command command1) throws Exception {
        String[] input = {"command", "argument"};
        String[] exitInput = {"exit"};
        willCallRealMethod().given(mockView).displayMessage(anyString());
        given(mockView.getInput())
                .willReturn(input)
                .willReturn(exitInput);

        will((invocationOnMock -> {
            Arrays.stream(input).forEach(System.out::println);
            return null;
        })).given(command1).execute();

        given(commandSupplier.fetchCommand(any(), any()))
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
    void WhenUserInputsMultipleCommandsWithArguments_CommandsAreExecuted(@Mock Command command1,
                                                                         @Mock Command command2,
                                                                         @Mock Command command3
    ) throws Exception {
        String[] inputOne = {"one", "argument_one"};
        String[] inputTwo = {"two", "argument_two"};
        String[] inputThree = {"three"};
        String[] exitInput = {"exit"};
        given(mockView.getInput())
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

        given(commandSupplier.fetchCommand(any(), any()))
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
