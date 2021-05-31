package com.example.cryptogramgamewithspring.Controllers.Commands;

import com.example.cryptogramgamewithspring.Controllers.Commands.CommandSupplier.CommandSupplier;
import com.example.cryptogramgamewithspring.Controllers.Commands.CommandSupplier.GameContext;
import com.example.cryptogramgamewithspring.Controllers.GameplayController;
import com.example.cryptogramgamewithspring.Cryptogram.Cryptogram;
import com.example.cryptogramgamewithspring.Cryptogram.CryptogramRepository;
import com.example.cryptogramgamewithspring.Player.InvalidFileFormatException;
import com.example.cryptogramgamewithspring.Player.Player;
import com.example.cryptogramgamewithspring.Presentation.ConsoleView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static uk.org.webcompere.systemstubs.SystemStubs.tapSystemOut;

@ExtendWith(MockitoExtension.class)
public class playLoadedCryptogramCommandTests {

    @Mock
    private ConsoleView mockView;
    @Mock
    private CryptogramRepository cryptogramRepository;
    @Mock
    private Player player;
    @Mock
    private CommandSupplier<GameContext> supplier;

    @Test
    void givenUserExecutesLoadGame_whenEverythingIsFineWithCryptogramRepo_ThenCryptogramIsLoadedAndGameLaunches() throws Exception {

        //given
        String username = "test_user";
        given(player.getUsername()).willReturn(username);
        given(cryptogramRepository.loadCryptogram(anyString())).willReturn(Optional.of(new Cryptogram("a")));
        given(mockView.getInput()).willReturn(new String[]{"exit"});
        willCallRealMethod().given(mockView).displayMessage(anyString());

        // when
        playLoadedCryptogramCommand command = new playLoadedCryptogramCommand(mockView, cryptogramRepository, player, supplier);
        String output = tapSystemOut(command::execute);
        assertEquals(">>>>> Type help to list all available commands.\n" +
                "\n" +
                ">>>>> Please enter a mapping in format <letter><space><cryptogram value>:\n" +
                "\n", output);

        // then
        verify(cryptogramRepository).loadCryptogram(username);
    }

    @Test
    void givenUserExecutesNewGame_NoFileIsStored_ThenCryptogramIsNotGeneratedAndGameDoesntLaunch() throws Exception {

        //given
        given(player.getUsername()).willReturn("test_user");
        willCallRealMethod().given(mockView).displayMessage(any());
        given(cryptogramRepository.loadCryptogram(any())).willReturn(Optional.empty());

        // when
        playLoadedCryptogramCommand command = new playLoadedCryptogramCommand(mockView, cryptogramRepository, player, supplier);
        String output = tapSystemOut(command::execute);
        assertEquals(">>>>> No cryptogram stored, Try creating a new one.\n\n", output);

        // then
        verify(cryptogramRepository).loadCryptogram(any());
    }

    @Test
    void givenUserExecutesNewGame_FileIsCorrupted_ThenCryptogramIsNotGeneratedAndGameDoesntLaunch() throws Exception {

        //given
        given(player.getUsername()).willReturn("test_user");
        willCallRealMethod().given(mockView).displayMessage(any());
        given(cryptogramRepository.loadCryptogram(any())).willThrow(InvalidFileFormatException.class);

        // when
        playLoadedCryptogramCommand command = new playLoadedCryptogramCommand(mockView, cryptogramRepository, player, supplier);
        String output = tapSystemOut(command::execute);
        assertEquals(">>>>> File is corrupted. Either remove the file manually or play and save a new game to be able to load.\n\n", output);

        // then
        verify(cryptogramRepository).loadCryptogram(any());
    }

}

