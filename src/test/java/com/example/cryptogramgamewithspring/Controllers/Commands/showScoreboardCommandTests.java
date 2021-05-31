package com.example.cryptogramgamewithspring.Controllers.Commands;

import com.example.cryptogramgamewithspring.Player.Player;
import com.example.cryptogramgamewithspring.Player.PlayerService;
import com.example.cryptogramgamewithspring.Presentation.ConsoleView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willCallRealMethod;
import static uk.org.webcompere.systemstubs.SystemStubs.tapSystemOut;

@ExtendWith(MockitoExtension.class)
public class showScoreboardCommandTests {

    @Mock
    private PlayerService playerService;
    @Mock
    private ConsoleView view;

    @Test
    void givenThereAreLessThan10Players_whenPlayerCallsScores_ScoreboardIsDisplayed() throws Exception {
        Map<Player, Double> accuracies = new HashMap<>();
        accuracies.put(new Player("a"), 5.9);
        accuracies.put(new Player("b"), 0.9);
        accuracies.put(new Player("c"), 0.6);
        accuracies.put(new Player("d"), 4.2);

        String output = getOutput(accuracies);

        assertEquals(">>>>> Top ten players by accuracy are:\n" +
                        "1. a: 5.9\n" +
                        "2. d: 4.2\n" +
                        "3. b: 0.9\n" +
                        "4. c: 0.6\n" +
                        "\n\n",
                output);
    }

    @Test
    void givenThereAreMoreThan10Players_whenPlayerCallsScores_ScoreboardIsDisplayed() throws Exception {
        Map<Player, Double> accuracies = new HashMap<>();
        accuracies.put(new Player("a"), 5.9);
        accuracies.put(new Player("b"), 2.9);
        accuracies.put(new Player("c"), 2.6);
        accuracies.put(new Player("d"), 1.3);
        accuracies.put(new Player("e"), 3.2);
        accuracies.put(new Player("f"), 6.2);
        accuracies.put(new Player("g"), 4.1);
        accuracies.put(new Player("h"), 0.1);
        accuracies.put(new Player("i"), 0.2);
        accuracies.put(new Player("j"), 1.2);
        accuracies.put(new Player("k"), 0.9);

        String output = getOutput(accuracies);

        assertEquals(">>>>> Top ten players by accuracy are:\n" +
                        "1. f: 6.2\n" +
                        "2. a: 5.9\n" +
                        "3. g: 4.1\n" +
                        "4. e: 3.2\n" +
                        "5. b: 2.9\n" +
                        "6. c: 2.6\n" +
                        "7. d: 1.3\n" +
                        "8. j: 1.2\n" +
                        "9. k: 0.9\n" +
                        "10. i: 0.2\n" +
                        "\n\n",
                output);
    }

    private String getOutput(Map<Player, Double> accuracies) throws Exception {
        willCallRealMethod().given(view).displayMessage(any());
        given(playerService.getAccuracies()).willReturn(accuracies);
        showScoreboardCommand command = new showScoreboardCommand(view, playerService);
        return tapSystemOut(command::execute);
    }

    @Test
    void givenNoPlayers_whenScoresIsCalled_NoScoresDisplayed() throws Exception {
        Map<Player, Double> accuracies = new HashMap<>();
        String output = getOutput(accuracies);
        assertEquals(">>>>> No players found :( sorry\n\n" , output);
    }
}
