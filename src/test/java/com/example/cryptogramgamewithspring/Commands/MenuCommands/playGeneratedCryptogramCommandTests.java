package com.example.cryptogramgamewithspring.Commands.MenuCommands;

import com.example.cryptogramgamewithspring.Commands.Commands.playGeneratedCryptogramCommand;
import com.example.cryptogramgamewithspring.Commands.Factories.CommandFactory;
import com.example.cryptogramgamewithspring.Controllers.GameContext;
import com.example.cryptogramgamewithspring.Controllers.MenuContext;
import com.example.cryptogramgamewithspring.Cryptogram.Cryptogram;
import com.example.cryptogramgamewithspring.Cryptogram.CryptogramRepository;
import com.example.cryptogramgamewithspring.Player.Player;
import com.example.cryptogramgamewithspring.Player.PlayerService;
import com.example.cryptogramgamewithspring.Presentation.ConsoleView;
import com.example.cryptogramgamewithspring.Presentation.InputPrompt;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willCallRealMethod;
import static org.mockito.Mockito.verify;
import static uk.org.webcompere.systemstubs.SystemStubs.tapSystemOut;

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
    @Mock
    private CryptogramRepository cryptogramRepository;
    @Mock
    private Player player;

    private final String[] initialCommand = {""};

    @Test
    void givenUserExecutesNewGame_whenEverythingIsFineWithCryptogramRepo_ThenCryptogramIsGeneratedAndGameLaunches() throws Exception {
        //given
        given(mockPrompt.getInput()).willReturn(new String[] {"exit"});
        given(cryptogramRepository.generateCryptogram(any())).willReturn(Optional.of(new Cryptogram("a")));
        willCallRealMethod().given(mockView).displayMessage(any());
        MenuContext context = new MenuContext(mockPrompt,
                mockView,
                players,
                mockCommandFactory,
                initialCommand,
                cryptogramRepository,
                player);
        // when
        playGeneratedCryptogramCommand command = new playGeneratedCryptogramCommand(context);
        String output = tapSystemOut(command::execute);
        // then
        verify(cryptogramRepository).generateCryptogram(any());
        assertEquals(">>>>> Type help to list all available commands.\n" +
                "\n" +
                ">>>>> Please enter a mapping in format <letter><space><cryptogram value>:\n" +
                "\n",
                output );
        assertEquals(context, command.getState());
    }

}