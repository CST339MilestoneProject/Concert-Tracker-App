package com.gcu.trackerapp.controller;

import com.gcu.trackerapp.model.Concert;
import com.gcu.trackerapp.service.ConcertService;
import com.gcu.trackerapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ConcertController {

    @Autowired
    private ConcertService concertService;

    @Autowired
    private UserService userService;

    // MVC Controller: Show concerts
    @GetMapping("/concerts")
    public String showConcerts(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Long userId = userService.findByUsername(username).getId();
        List<Concert> userConcerts = concertService.getConcertsByUserId(userId);
        model.addAttribute("concerts", userConcerts);
        model.addAttribute("userId", userId);
        return "concerts";
    }

    // MVC Controller: Show concert details
    @GetMapping("/concerts/{id}")
    public String showConcertDetails(@PathVariable("id") Long id, Model model) {
        Concert concert = concertService.getConcertById(id);
        model.addAttribute("concert", concert);
        return "currentConcert";
    }

    // MVC Controller: Add concert
    @PostMapping("/concerts/add")
    public String addConcert(@ModelAttribute("concert") Concert concert, BindingResult result, @RequestParam("userId") Long userId) {
        if (result.hasErrors()) {
            return "addConcert";
        }
        concertService.addConcertWithUser(concert, userId);
        return "redirect:/concerts";
    }

    // MVC Controller: Delete concert
    @GetMapping("/concerts/delete/{id}")
    public String deleteConcert(@PathVariable("id") Long id) {
        concertService.deleteConcertById(id);
        return "redirect:/concerts";
    }

    // MVC Controller: Show edit concert form
    @GetMapping("/concerts/edit/{id}")
    public String showEditConcertForm(@PathVariable("id") Long id, Model model) {
        Concert concert = concertService.getConcertById(id);
        if (concert != null) {
            model.addAttribute("concert", concert);
            return "editConcert";
        } else {
            return "redirect:/concerts";
        }
    }

    // MVC Controller: Update concert
    @PostMapping("/concerts/edit/{id}")
    public String updateConcert(@PathVariable("id") Long id, @ModelAttribute("concert") Concert concert, BindingResult result) {
        if (result.hasErrors()) {
            return "editConcert";
        }
        concert.setId(id);
        concertService.updateConcert(concert);
        return "redirect:/concerts";
    }

    // REST API: Get all concerts
    @GetMapping("/api/concerts")
    public ResponseEntity<List<Concert>> getAllConcertsApi() {
        List<Concert> concerts = concertService.getAllConcerts();
        return concerts.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(concerts, HttpStatus.OK);
    }

    // REST API: Get concert by ID
    @GetMapping("/api/concerts/{id}")
    public ResponseEntity<Concert> getConcertByIdApi(@PathVariable("id") Long id) {
        Concert concert = concertService.getConcertById(id);
        return concert != null ? new ResponseEntity<>(concert, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}