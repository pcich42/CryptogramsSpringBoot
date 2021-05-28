package com.example.cryptogramgamewithspring.Commands.MenuCommands;

import com.example.cryptogramgamewithspring.Commands.Commands.Command;
import com.example.cryptogramgamewithspring.Commands.Commands.playGeneratedCryptogramCommand;
import com.example.cryptogramgamewithspring.Commands.Factories.CommandFactory;
import com.example.cryptogramgamewithspring.Controllers.GameContext;
import com.example.cryptogramgamewithspring.Cryptogram.Cryptogram;
import com.example.cryptogramgamewithspring.Player.Player;
import com.example.cryptogramgamewithspring.Presentation.ConsoleView;
import com.example.cryptogramgamewithspring.Presentation.InputPrompt;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Disabled
class playGeneratedCryptogramCommandTests {

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

    @Test
    void test() {
        GameContext context = new GameContext(cryptogram, player, mockView, mockPrompt, mockCommandFactory);
        playGeneratedCryptogramCommand command = new playGeneratedCryptogramCommand();
        command.execute();
        GameContext expected = new GameContext(cryptogram, player, mockView, mockPrompt, mockCommandFactory);
        assertEquals(expected, command.getState());
    }

}