package com.example.cryptogramgamewithspring.Commands.MenuCommands;

import com.example.cryptogramgamewithspring.Commands.Commands.playGeneratedCryptogramCommand;
import com.example.cryptogramgamewithspring.Commands.Factories.CommandFactory;
import com.example.cryptogramgamewithspring.Controllers.GameContext;
import com.example.cryptogramgamewithspring.Controllers.MenuContext;
import com.example.cryptogramgamewithspring.Player.PlayerService;
import com.example.cryptogramgamewithspring.Presentation.ConsoleView;
import com.example.cryptogramgamewithspring.Presentation.InputPrompt;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class playGeneratedCryptogramCommandTests {

    @Mock
    private ConsoleView mockView;
    @Mock
    private InputPrompt mockPrompt;
    @Mock
    private CommandFactory<GameContext> mockCommandFactory;
    @Mock
    private PlayerService players;

    private final String[] input = {""};

    @Test
    void test() throws IOException {
        willDoNothing().given(players).savePlayers();
        MenuContext context = new MenuContext(mockPrompt, mockView, players, mockCommandFactory, input);
        playGeneratedCryptogramCommand command = new playGeneratedCryptogramCommand(context);
        command.execute();
        MenuContext expected = new MenuContext(mockPrompt, mockView, players, mockCommandFactory, input);
        verify(players).savePlayers();
        assertEquals(expected, command.getState());
    }

}