package com.gcu.trackerapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller for handling login requests.
 */
@Controller
public class LogInController {

    /**
     * Displays the login form.
     *
     * @param model The model for the view.
     * @param error Error message attribute for login failure.
     * @param logout Logout message attribute for successful logout.
     * @return The login form view name.
     */
    @GetMapping("/login")
    public String showLoginForm(Model model, @RequestParam(value = "error", required = false) String error,
                                @RequestParam(value = "logout", required = false) String logout) {
        if (error != null) {
            model.addAttribute("loginError", "Invalid username or password.");
        }

        if (logout != null) {
            model.addAttribute("logoutMessage", "Logged out successfully.");
        }

        return "login";
    }

    /**
     * Processes the login request.
     *
     * @return Redirects to the homepage upon successful login.
     */
    @PostMapping("/login")
    public String processLogin() {
        return "redirect:/";
    }
}
