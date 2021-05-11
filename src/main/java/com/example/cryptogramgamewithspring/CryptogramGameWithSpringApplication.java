package com.example.cryptogramgamewithspring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CryptogramGameWithSpringApplication {

    private static final Logger LOG = LoggerFactory
            .getLogger(CryptogramGameWithSpringApplication.class);

    public static void main(String[] args) {
        LOG.info("STARTING CRYPTOGRAMS...");
        SpringApplication.run(CryptogramGameWithSpringApplication.class, args);
        LOG.info("APPLICATION CLOSED");
    }

}
