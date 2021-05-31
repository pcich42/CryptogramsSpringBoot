package com.example.cryptogramgamewithspring.Controllers.Commands;

import com.example.cryptogramgamewithspring.Controllers.GameplayController;
import com.example.cryptogramgamewithspring.Cryptogram.Cryptogram;
import com.example.cryptogramgamewithspring.Cryptogram.CryptogramRepository;
import com.example.cryptogramgamewithspring.Player.Player;
import com.example.cryptogramgamewithspring.Presentation.ConsoleView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.verify;
import static uk.org.webcompere.systemstubs.SystemStubs.tapSystemOut;

@ExtendWith(MockitoExtension.class)
class playGeneratedCryptogramCommandTests {

    @Mock
    private GameplayController gameplayController;
    @Mock
    private ConsoleView mockView;
    @Mock
    private CryptogramRepository cryptogramRepository;
    @Mock
    private Player player;

    @ParameterizedTest
    @CsvSource({"new letters, letters",
            "new, letters",
            "new numbers, numbers",
            "new aa, invalid"})
    void givenUserExecutesNewGame_whenEverythingIsFineWithCryptogramRepo_ThenCryptogramIsGeneratedAndGameLaunches(String input, String output) throws IOException {

        //given
        String[] initialCommand = input.split(" ");
        given(cryptogramRepository.generateCryptogram(any())).willReturn(Optional.of(new Cryptogram("a")));
        willDoNothing().given(gameplayController).mainLoop();

        // when
        playGeneratedCryptogramCommand command = new playGeneratedCryptogramCommand(gameplayController, mockView, cryptogramRepository, player, initialCommand);
        command.execute();

        // then
        verify(cryptogramRepository).generateCryptogram(output);
        verify(gameplayController).mainLoop();
    }

    @Test
    void givenUserExecutesNewGame_whenCryptoRepoCantRetrieveCryptogram_ThenCryptogramIsNotGeneratedAndGameDoesntLaunch() throws Exception {

        //given
        String[] initialCommand = new String[]{"new"};
        willCallRealMethod().given(mockView).displayMessage(any());
        given(cryptogramRepository.generateCryptogram(any())).willThrow(IOException.class);

        // when
        playGeneratedCryptogramCommand command = new playGeneratedCryptogramCommand(gameplayController, mockView, cryptogramRepository, player, initialCommand);
        String output = tapSystemOut(command::execute);
        assertEquals(">>>>> Couldn't generate Cryptogram.\n\n", output);

        // then
        verify(cryptogramRepository).generateCryptogram(any());

    }

    @Test
    void givenUserExecutesNewGameIncorrectly_whenEverythingIsFineWithCryptogramRepo_ThenCryptogramIsNotGeneratedAndGameDoesntLaunch() throws Exception {

        //given
        String[] initialCommand = new String[]{"new", "aa"};
        willCallRealMethod().given(mockView).displayMessage(any());
        given(cryptogramRepository.generateCryptogram("invalid")).willReturn(Optional.empty());

        // when
        playGeneratedCryptogramCommand command = new playGeneratedCryptogramCommand(gameplayController, mockView, cryptogramRepository, player, initialCommand);
        String output = tapSystemOut(command::execute);
        assertEquals(">>>>> Unexpected cryptogram type. Try letters or numbers.\n\n", output);

        // then
        verify(cryptogramRepository).generateCryptogram("invalid");
    }

}