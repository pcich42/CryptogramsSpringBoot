package com.example.cryptogramgamewithspring.InputOutput;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class InputPrompt {
    private final Scanner scanner;

    public InputPrompt() {
        this.scanner = new Scanner(System.in);
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
