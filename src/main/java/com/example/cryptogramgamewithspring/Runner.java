package com.example.cryptogramgamewithspring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.example.cryptogramgamewithspring.Controllers.*;


@Component
public class Runner implements CommandLineRunner {

    private final ApplicationContext context;

    public Runner(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public void run(String... args) {
        var game = context.getBean(Menu.class);
        game.mainLoop();
    }
}
