package com.example.cryptogramgamewithspring.Player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class PlayersServiceTests {

    @Mock
    private FilePlayerRepository playerRepository;

    private PlayerService playerService;

    @BeforeEach
    void setUp() {
        playerService = new PlayerService(playerRepository);
    }

    @Test
    void addPlayerTest() {
        Player player = mock(Player.class);
        assertTrue(playerService.addPlayer(player));
    }

    @Test
    void addMultiplePlayers() {
        Player player1 = mock(Player.class);
        Player player2 = mock(Player.class);
        Player player3 = mock(Player.class);
        assertTrue(playerService.addPlayer(player1));
        assertTrue(playerService.addPlayer(player2));
        assertTrue(playerService.addPlayer(player3));
    }

    @Test
    void getPlayerTest() {
        String username = "example_username";
        Player mock_player = mock(Player.class);
        given(mock_player.getUsername()).willReturn(username);

        playerService.addPlayer(mock_player);

        Player player = playerService.getPlayer(username);
        assertEquals(username, player.getUsername());
    }

    @Test
    void getMultiplePlayers() {
        String username1 = "a";
        String username2 = "b";
        String username3 = "c";

        Player mock_player1 = mock(Player.class);
        Player mock_player2 = mock(Player.class);
        Player mock_player3 = mock(Player.class);

        given(mock_player1.getUsername()).willReturn(username1);
        given(mock_player2.getUsername()).willReturn(username2);
        given(mock_player3.getUsername()).willReturn(username3);

        playerService.addPlayer(mock_player1);
        playerService.addPlayer(mock_player2);
        playerService.addPlayer(mock_player3);

        Player player1 = playerService.getPlayer(username1);
        Player player2 = playerService.getPlayer(username2);
        Player player3 = playerService.getPlayer(username3);

        assertEquals(player1.getUsername(), username1);
        assertEquals(player2.getUsername(), username2);
        assertEquals(player3.getUsername(), username3);
    }

    @Test
    void ifPlayerDoesntExits_NoSuchElementIsThrown() {
        assertThrows(NoSuchElementException.class, () -> playerService.getPlayer("test_player"));
    }

    @Test
    void getAccuraciesTest() {
        Player player1 = mock(Player.class);
        Player player2 = mock(Player.class);
        Player player3 = mock(Player.class);

        given(player1.getAccuracy()).willReturn(3.0);
        given(player2.getAccuracy()).willReturn(50.0);
        given(player3.getAccuracy()).willReturn(99.0);

        playerService.addPlayer(player1);
        playerService.addPlayer(player2);
        playerService.addPlayer(player3);

        SortedMap<Double, Player> accuracies = new TreeMap<>();
        accuracies.put(3.0, player1);
        accuracies.put(50.0, player2);
        accuracies.put(99.0, player3);

        assertEquals(accuracies, playerService.getAccuracies());

    }

    @Test
    void whenFileExists_savePlayersToDatabase_DoesNotThrow() throws IOException {
        willDoNothing().given(playerRepository).savePlayers(anySet());
        assertDoesNotThrow(() -> playerService.savePlayers());
    }

    @Test
    void whenFileDoesntExist_savePlayersToDatabase_ThrowsFileNotFound() throws IOException {
        willThrow(FileNotFoundException.class).given(playerRepository).savePlayers(anySet());
        assertThrows(FileNotFoundException.class, () -> playerService.savePlayers());
    }

}
