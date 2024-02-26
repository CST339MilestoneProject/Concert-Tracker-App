package com.gcu.trackerapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TrackerApplication {

	public static void main(String[] args) {
		System.err.println("Test");
		SpringApplication.run(TrackerApplication.class, args);
	}

}
