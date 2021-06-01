package com.example.cryptogramgamewithspring.Presentation;

import com.example.cryptogramgamewithspring.Cryptogram.Cryptogram;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleView {

    private final Scanner scanner;

    public ConsoleView() {
        this.scanner = new Scanner(System.in);
    }

    public void displayMessage(String message) {
        System.out.println(">>>>> " + message);
        System.out.println();
    }

    public void displayHelp() {
    }

    public void displayCryptogram(Cryptogram cryptogram) {
    }

    public String[] getInput() {
        return scanner.nextLine().toLowerCase().split(" ");
    }

    public boolean confirmChoice() {

        boolean answer;
        while (true) {
            String confirmation = scanner.nextLine();
            if (confirmation.equalsIgnoreCase("y")
                    || confirmation.equalsIgnoreCase("yes")) {
                answer = true;
                break;
            } else if (confirmation.equalsIgnoreCase("n")
                    || confirmation.equalsIgnoreCase("no")) {
                answer = false;
                break;
            } else {
                System.out.println("Invalid response, try again with \"yes\" or \"no\" ");
            }
        }
        return answer;
    }

}
