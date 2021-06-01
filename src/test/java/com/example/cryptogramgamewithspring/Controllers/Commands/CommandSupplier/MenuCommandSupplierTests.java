package com.example.cryptogramgamewithspring.Controllers.Commands.CommandSupplier;

import com.example.cryptogramgamewithspring.Controllers.Commands.Command;
import com.example.cryptogramgamewithspring.Cryptogram.CryptogramRepository;
import com.example.cryptogramgamewithspring.Player.PlayerService;
import com.example.cryptogramgamewithspring.Presentation.ConsoleView;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.willCallRealMethod;
import static uk.org.webcompere.systemstubs.SystemStubs.tapSystemOut;

@ExtendWith(MockitoExtension.class)
public class MenuCommandSupplierTests {

    @Mock
    private ConsoleView view;
    @Mock
    private CryptogramRepository cryptogramRepository;
    @Mock
    private PlayerService playerService;
    @Mock
    private CommandSupplier<GameContext> commandSupplier;
    @Mock
    private MenuContext context;

    private MenuCommandSupplier factory;

    @BeforeEach
    void setUp() {
        factory = new MenuCommandSupplier(view, cryptogramRepository, playerService, commandSupplier);
    }

    @ParameterizedTest
    @CsvSource({"new, com.example.cryptogramgamewithspring.Controllers.Commands.PlayGeneratedCryptogramCommand",
    "new letters, com.example.cryptogramgamewithspring.Controllers.Commands.PlayGeneratedCryptogramCommand",
    "load, com.example.cryptogramgamewithspring.Controllers.Commands.PlayLoadedCryptogramCommand",
    "scores, com.example.cryptogramgamewithspring.Controllers.Commands.ShowScoreboardCommand"})
    void testTest(String input, Class<?> commandType) {
        Command command = factory.fetchCommand(input.split(" ")[0], context);
        assertTrue(commandType.isInstance(command));
    }

}
