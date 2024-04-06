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

/**
 * Controller for managing concert-related actions and RESTful services.
 */
@Controller
public class ConcertController {

    @Autowired
    private ConcertService concertService;

    @Autowired
    private UserService userService;

    /**
     * Displays a list of concerts to the user.
     *
     * @param model The model to be populated for the view.
     * @return The name of the view to display.
     */
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

    /**
     * Displays details for a specific concert.
     *
     * @param id The ID of the concert to display.
     * @param model The model to be populated for the view.
     * @return The name of the view to display.
     */
    @GetMapping("/concerts/{id}")
    public String showConcertDetails(@PathVariable("id") Long id, Model model) {
        Concert concert = concertService.getConcertById(id);
        model.addAttribute("concert", concert);
        return "currentConcert";
    }

    /**
     * Handles the submission of a new concert addition.
     *
     * @param concert The concert to be added.
     * @param result The binding result for error handling.
     * @param userId The ID of the user adding the concert.
     * @return Redirect to the concerts list view.
     */
    @PostMapping("/concerts/add")
    public String addConcert(@ModelAttribute("concert") Concert concert, BindingResult result, @RequestParam("userId") Long userId) {
        if (result.hasErrors()) {
            return "addConcert";
        }
        concertService.addConcertWithUser(concert, userId);
        return "redirect:/concerts";
    }

    /**
     * Handles the deletion of a concert.
     *
     * @param id The ID of the concert to delete.
     * @return Redirect to the concerts list view.
     */
    @GetMapping("/concerts/delete/{id}")
    public String deleteConcert(@PathVariable("id") Long id) {
        concertService.deleteConcertById(id);
        return "redirect:/concerts";
    }

    /**
     * Displays the form for editing a concert.
     *
     * @param id The ID of the concert to edit.
     * @param model The model to be populated for the view.
     * @return The name of the view to display.
     */
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

    /**
     * Handles the submission of concert edits.
     *
     * @param id The ID of the concert to update.
     * @param concert The updated concert details.
     * @param result The binding result for error handling.
     * @return Redirect to the concerts list view.
     */
    @PostMapping("/concerts/edit/{id}")
    public String updateConcert(@PathVariable("id") Long id, @ModelAttribute("concert") Concert concert, BindingResult result) {
        if (result.hasErrors()) {
            return "editConcert";
        }
        concert.setId(id);
        concertService.updateConcert(concert);
        return "redirect:/concerts";
    }

    /**
     * Provides a list of all concerts via REST API.
     *
     * @return A ResponseEntity containing the list of concerts.
     */
    @GetMapping("/api/concerts")
    public ResponseEntity<List<Concert>> getAllConcertsApi() {
        List<Concert> concerts = concertService.getAllConcerts();
        return concerts.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(concerts, HttpStatus.OK);
    }

    /**
     * Provides details of a specific concert by its ID via REST API.
     *
     * @param id The ID of the concert.
     * @return A ResponseEntity containing the concert details.
     */
    @GetMapping("/api/concerts/{id}")
    public ResponseEntity<Concert> getConcertByIdApi(@PathVariable("id") Long id) {
        Concert concert = concertService.getConcertById(id);
        return concert != null ? new ResponseEntity<>(concert, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
