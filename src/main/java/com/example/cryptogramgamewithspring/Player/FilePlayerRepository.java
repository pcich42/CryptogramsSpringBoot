package com.example.cryptogramgamewithspring.Player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

@Repository
public class FilePlayerRepository implements PlayerRepository {
    private final String filepath;

    @Autowired
    public FilePlayerRepository(@Value("TestFiles/TestPlayerFile.txt") String filepath) {
        this.filepath = filepath;
    }

    public void savePlayers(Set<Player> players) throws IOException {
        if (filepath.isEmpty()) throw new FileNotFoundException();
        savePlayersToDatabase(filepath, players);
    }

    public void savePlayersToDatabase(String filepath, Set<Player> players) throws IOException {
        File file = getDatabaseFile(filepath);
        writePlayerDetails(players, file);
    }

    private void writePlayerDetails(Set<Player> players, File file) throws IOException {
        FileWriter writer = new FileWriter(file);
        String lineSeparator = ";" + System.lineSeparator();
        for (Player player : players) writePlayerDetailsLine(writer, lineSeparator, player);
        writer.close();
    }

    private void writePlayerDetailsLine(FileWriter writer, String lineSeparator, Player player) throws IOException {
        String line = player.getUsername() + "," + player.getAccuracy().toString();
        writer.write(line);
        writer.write(lineSeparator);
    }

    private File getDatabaseFile(String filepath) throws IOException {
        File file = new File(filepath);
        if (!file.exists()) file.createNewFile();
        return file;
    }

    public Set<Player> getPlayers() throws IOException, InvalidFileFormatException {
        Set<Player> players = new HashSet<>();

        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        String line;
        while((line = reader.readLine()) != null) {
            validateLine(line);
            String[] tokens = line.split(",");
            Player player = new Player(tokens[0]);
            players.add(player);
        }
        return players;
    }

    private void validateLine(String line) throws InvalidFileFormatException {
        if (
                !line.endsWith(";") ||
                !line.contains(",") ||
                line.split(",").length != 2
        ) throw new InvalidFileFormatException();
    }

}
