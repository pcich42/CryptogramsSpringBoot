package com.example.cryptogramgamewithspring.Controllers.Commands.CommandSupplier;

import com.example.cryptogramgamewithspring.Controllers.Commands.*;
import com.example.cryptogramgamewithspring.Player.PlayerService;
import com.example.cryptogramgamewithspring.Presentation.ConsoleView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.willCallRealMethod;
import static uk.org.webcompere.systemstubs.SystemStubs.tapSystemOut;

@ExtendWith(MockitoExtension.class)
public class GameplayCommandSupplierTests {

    @Mock
    private GameContext context;
    @Mock
    private PlayerService playerService;
    @Mock
    private ConsoleView view;

    private GameplayCommandSupplier factory;

    @BeforeEach
    void setUp() {
        factory = new GameplayCommandSupplier(view, playerService);
    }


    @ParameterizedTest
    @CsvSource({"random string input, com.example.cryptogramgamewithspring.Controllers.Commands.EnterGuessCommand",
            "p o, com.example.cryptogramgamewithspring.Controllers.Commands.EnterGuessCommand",
            "undo, com.example.cryptogramgamewithspring.Controllers.Commands.UndoCommand",
            "solution, com.example.cryptogramgamewithspring.Controllers.Commands.ShowSolutionCommand",
            "freq, com.example.cryptogramgamewithspring.Controllers.Commands.ShowFrequenciesCommand",
            "exit, com.example.cryptogramgamewithspring.Controllers.Commands.ExitCommand",
            "hint, com.example.cryptogramgamewithspring.Controllers.Commands.HintCommand",
            "save, com.example.cryptogramgamewithspring.Controllers.Commands.SaveGameCommand",
            "help, com.example.cryptogramgamewithspring.Controllers.Commands.DisplayHelpCommand"})
    void testTest(String input, Class<?> commandType) {
        Command command = factory.fetchCommand(input.split(" ")[0], context);
        assertTrue(commandType.isInstance(command));
    }

    @Test
    void displayHelpWorksAsExpected() throws Exception {
        willCallRealMethod().given(view).displayMessage(anyString());
        Command command = factory.fetchCommand("help", context);
        String output = tapSystemOut(command::execute);
        assertEquals(    ">>>>> All available commands are:\n" +
                "<guess> <crypto value> - maps guess to the encrypted value\n" +
                "undo <guess> - removes a guess from solution\n" +
                "hint - provides a hint\n" +
                "solution - displays a solution, no points are awarded for this game\n" +
                "freq - displays the relative frequencies of values of the cryptogram and frequencies of usage of letters in the English language\n" +
                "scores - displays top ten players by accuracy\n" +
                "save - saves this game\n" +
                "help - displays this prompt\n"+
                "exit - exits the game\n\n", output);
    }

}
