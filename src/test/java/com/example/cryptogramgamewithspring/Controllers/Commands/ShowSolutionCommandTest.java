package com.example.cryptogramgamewithspring.Controllers.Commands;

import com.example.cryptogramgamewithspring.Controllers.Commands.CommandSupplier.GameContext;
import com.example.cryptogramgamewithspring.Cryptogram.Cryptogram;
import com.example.cryptogramgamewithspring.Player.Player;
import com.example.cryptogramgamewithspring.Presentation.ConsoleView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willCallRealMethod;
import static org.mockito.Mockito.verify;
import static uk.org.webcompere.systemstubs.SystemStubs.tapSystemOut;

@ExtendWith(MockitoExtension.class)
class ShowSolutionCommandTest {

    @Mock
    private ConsoleView view;
    @Mock
    private Cryptogram cryptogram;
    @Mock
    private Player player;
    private final String[] input = new String[]{""};
    private ShowSolutionCommand command;

    @BeforeEach
    void setUp() {
        GameContext context = new GameContext(cryptogram, player, input);
        command = new ShowSolutionCommand(view, context);
    }

    @Test
    void execute_displaysTheSolution() throws Exception {
        willCallRealMethod().given(view).displayMessage(anyString());
        given(cryptogram.parseToString()).willReturn("Cryptogram solution parsed");

        String output = tapSystemOut(command::execute);
        verify(cryptogram).fillSolution();
        assertEquals(">>>>> The solution is:\n" +
                "Cryptogram solution parsed\n\n" , output);
    }

    @Test
    void commandAlwaysExits() {
        assertTrue(command.didExit());
    }

}