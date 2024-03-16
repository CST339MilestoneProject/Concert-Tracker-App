package com.gcu.trackerapp.controller;

import com.gcu.trackerapp.model.User;
import com.gcu.trackerapp.service.UserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class LogInController {

    private static final Logger logger = LoggerFactory.getLogger(LogInController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session) {
        if (userService.validateUser(username, password)) {
            User user = userService.findByUsername(username);
            session.setAttribute("userId", user.getId());
            logger.info("User logged in with ID: {}", user.getId());
            return "redirect:/concerts";
        } else {
            return "redirect:/login?error";
        }
    }
}
