package com.gcu.trackerapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Main controller for handling the root path requests.
 */
@Controller
public class MainController {

    /**
     * Displays the homepage.
     *
     * @return The homepage view name.
     */
    @GetMapping("/")
    public String showHomePage() {
        return "index";
    }
}
