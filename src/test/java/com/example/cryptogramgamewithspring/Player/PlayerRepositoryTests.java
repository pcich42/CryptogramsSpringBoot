package com.example.cryptogramgamewithspring.Player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerRepositoryTests {

    FilePlayerRepository repository;
    File file;

    @BeforeEach
    void setUp(@TempDir Path dir) {
        file = dir.resolve("TestPlayerFiles").toFile();
        repository = new FilePlayerRepository(file.toString());
    }

    @Test
    void givenFileDoesNotExists_whenUserSavesProgress_FileGetsCreated() throws IOException {

        // given file does not yet exist
        assertFalse(file.exists());

        // when we want to save to the database
        Set<Player> players = new HashSet<>();
        repository.savePlayers(players);

        // the file gets created
        assertTrue(file.exists());

    }

    @Test
    void givenNoPreviousDetailsSaved_whenPlayerSavesProgress_playersAreSaved() throws IOException {

        // given no details are saved
        assertTrue(file.createNewFile());
        assertTrue(getLinesFromFile(file).isEmpty());
        // when we want to save to the database
        Set<Player> players = getPlayers("player1", "player2", "player3");
        repository.savePlayers( players);

        // player details are saved
        List<String> result = getLinesFromFile(file);
        players.forEach(player -> assertTrue(result.contains(getDetailsString(player))));
    }

    @Test
    void givenPreviousDetailsAreSaved_whenPlayerSavesProgress_DetailsAreOverridden() throws IOException {

        // given a file with some details exists
        assertTrue(file.createNewFile());
        List<String> previousDetails = List.of(
                "player4,5.6;",
                "player5,6.9;",
                "player6,0.1;");
        Files.write(file.toPath(), previousDetails);
        assertTrue(getLinesFromFile(file).contains("player4,5.6;"));

        // when saving players
        Set<Player> players = getPlayers("player7", "player8", "player9");
        repository.savePlayers(players);
        List<String> result = getLinesFromFile(file);

        // previously stored details are removed
        previousDetails.forEach(line -> assertFalse(result.contains(line)));

        // and new details are written
        players.forEach(player -> assertTrue(result.contains(getDetailsString(player))));
    }

    private Set<Player> getPlayers(String ... usernames) {
        Set<Player> players = new HashSet<>();
        for(String username : usernames) players.add(new Player(username));
        return players;
    }

    private List<String> getLinesFromFile(File file) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        return reader.lines().collect(Collectors.toList());
    }

    private String getDetailsString(Player player) {
        return player.getUsername() + "," + player.getAccuracy() + ";";
    }

    @Test
    void givenFileExists_whenLoadPlayersIsCalled_returnsPlayers() throws IOException, InvalidFileFormatException {
        // given file exists and has some contents
        assertTrue(file.createNewFile());
        assertTrue(file.exists());

        List<String> details = List.of(
                "player10,1.0;",
                "player11,1.0;",
                "player12,1.0;"
        );
        Files.write(file.toPath(), details);

        assertTrue(getLinesFromFile(file).contains("player10,1.0;"));

        // when get players is called
        Set<Player> players = repository.getPlayers();
        Set<Player> expectedPlayers = getPlayers("player10", "player11", "player12");

        // a set of players is returned
        assertTrue(players.containsAll(expectedPlayers));
    }

}
