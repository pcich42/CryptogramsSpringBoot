package com.example.cryptogramgamewithspring.Controllers.Commands.CommandSupplier;

import com.example.cryptogramgamewithspring.Controllers.Commands.Command;
import com.example.cryptogramgamewithspring.Controllers.Commands.playGeneratedCryptogramCommand;
import com.example.cryptogramgamewithspring.Controllers.Commands.playLoadedCryptogramCommand;
import com.example.cryptogramgamewithspring.Controllers.Commands.showScoreboardCommand;
import com.example.cryptogramgamewithspring.Controllers.GameplayController;
import com.example.cryptogramgamewithspring.Cryptogram.CryptogramRepository;
import com.example.cryptogramgamewithspring.Player.Player;
import com.example.cryptogramgamewithspring.Player.PlayerService;
import com.example.cryptogramgamewithspring.Presentation.ConsoleView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class MenuCommandSupplierTests {

    @Mock
    private Player player;
    @Mock
    private ConsoleView view;
    @Mock
    private GameplayController gameplayController;
    @Mock
    private CryptogramRepository cryptogramRepository;
    @Mock
    private PlayerService playerService;

    private final MenuCommandSupplier factory = new MenuCommandSupplier(view, gameplayController, cryptogramRepository, playerService);

    private Command getCommand(String[] input) {
        return factory.fetchCommand(input, player);
    }

    @Test
    void input_new_returnsNewGameplayCommand() {
        String[] input = {"new"};
        Command command = getCommand(input);
        assertTrue(command instanceof playGeneratedCryptogramCommand);
    }

    @Test
    void input_new_cryptogramType_returnsNewGameplayCommand() {
        String[] input = {"new", "letters"};
        Command command = getCommand(input);
        assertTrue(command instanceof playGeneratedCryptogramCommand);
    }

    @Test
    void input_load_returnsNewGameplayCommand() {
        String[] input = {"load"};
        Command command = getCommand(input);
        assertTrue(command instanceof playLoadedCryptogramCommand);
    }

    @Test
    void input_scores_returnsNewGameplayCommand() {
        String[] input = {"scores"};
        Command command = getCommand(input);
        assertTrue(command instanceof showScoreboardCommand);
    }
}
