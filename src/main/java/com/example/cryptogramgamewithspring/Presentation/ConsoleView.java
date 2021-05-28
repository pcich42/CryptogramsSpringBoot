package com.example.cryptogramgamewithspring.Presentation;

import org.springframework.stereotype.Component;

@Component
public class ConsoleView {
    public void displayMessage(String message) {
        System.out.println(">>>>> " + message);
        System.out.println();
    }

    public void displayHelp() {
    }

    public void displayCryptogram() {
    }
}
