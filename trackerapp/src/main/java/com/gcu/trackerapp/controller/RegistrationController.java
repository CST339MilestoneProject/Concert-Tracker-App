package com.gcu.trackerapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.gcu.trackerapp.model.User;
import com.gcu.trackerapp.service.UserService;

/**
 * Controller for handling user registration requests.
 */
@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private UserService userService;

    /**
     * Displays the user registration form.
     *
     * @param model The model for the view.
     * @return The registration form view name.
     */
    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    /**
     * Processes the user registration submission.
     *
     * @param user   The user to be registered.
     * @param result Binding result for validation.
     * @return Redirects to the concerts view if successful, otherwise returns to the registration form.
     */
    @PostMapping
    public String processRegistration(@ModelAttribute("user") User user, BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        }
        userService.registerUser(user);
        return "redirect:/concerts";
    }
}
