package com.example.cryptogramgamewithspring.InputOutput;

import org.springframework.stereotype.Component;

@Component
public class View {
    public void displayMessage(String message) {
        System.out.println(">>>>> " + message);
        System.out.println();
    }
}
