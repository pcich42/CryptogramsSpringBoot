package com.example.cryptogramgamewithspring.Controllers.Commands;

import com.example.cryptogramgamewithspring.Controllers.Commands.CommandSupplier.GameContext;
import com.example.cryptogramgamewithspring.Cryptogram.Cryptogram;
import com.example.cryptogramgamewithspring.Player.Player;
import com.example.cryptogramgamewithspring.Presentation.ConsoleView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willCallRealMethod;
import static uk.org.webcompere.systemstubs.SystemStubs.tapSystemOut;

@ExtendWith(MockitoExtension.class)
class EnterGuessCommandTest {

    @Mock
    private Cryptogram cryptogram;
    @Mock
    private Player player;
    @Mock
    private ConsoleView view;

    @Test
    void whenPlayerMapsTheLastGuessCorrectly_CommandExits() throws Exception {
        willCallRealMethod().given(view).displayMessage(anyString());
        given(cryptogram.isFinished()).willReturn(true);
        given(cryptogram.isSolutionCorrect()).willReturn(true);

        String[] input = {"a x"};
        GameContext context = new GameContext(cryptogram, player, input);
        EnterGuessCommand command = new EnterGuessCommand(view, context);
        String output = tapSystemOut(command::execute);

        assertEquals(">>>>> Congratulations! You completed this cryptogram! We're now taking you back to menu!\n\n", output);
        assertTrue(command.didExit());
    }

    @Test
    void whenPlayerMapsTheLastGuessIncorrectly_CommandDoesntExit() throws Exception {
        willCallRealMethod().given(view).displayMessage(anyString());
        given(cryptogram.isFinished()).willReturn(true);
        given(cryptogram.isSolutionCorrect()).willReturn(false);

        String[] input = {"a x"};
        GameContext context = new GameContext(cryptogram, player, input);
        EnterGuessCommand command = new EnterGuessCommand(view, context);
        String output = tapSystemOut(command::execute);

        assertEquals(">>>>> Something seems off here... Try again.\n\n", output);
        assertFalse(command.didExit());
    }

    @Test
    void whenPlayerMapsNotLastGuess_CommandDoesntExit()  {
        given(cryptogram.isFinished()).willReturn(false);

        String[] input = {"a x"};
        GameContext context = new GameContext(cryptogram, player, input);
        EnterGuessCommand command = new EnterGuessCommand(view, context);
        command.execute();

        assertFalse(command.didExit());
    }

}