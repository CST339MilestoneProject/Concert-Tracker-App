package com.gcu.trackerapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main application class that serves as the entry point for the Spring Boot application.
 */
@SpringBootApplication
public class TrackerApplication {

    /**
     * The main method that starts the Spring Boot application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(TrackerApplication.class, args);
    }
}
