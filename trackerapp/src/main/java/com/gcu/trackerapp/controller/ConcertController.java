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

    @GetMapping("/concerts")
    public String showConcerts(Model model) {
        List<Concert> userConcerts = concertService.getAllConcerts();
        model.addAttribute("concerts", userConcerts);
        return "concerts";
    }

    @GetMapping("/concerts/add")
    public String showAddConcertForm(Model model) {
        model.addAttribute("concert", new Concert());
        return "addConcert";
    }


    @PostMapping("/concerts/add")
    public String addConcert(@ModelAttribute("concert") Concert concert, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "addConcert";
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        concert.setUserId(userService.findByUsername(username).getId());
        concertService.addConcert(concert);
        return "redirect:/concerts";
    }

    @GetMapping("/concerts/{id}")
    public String showConcertDetails(@PathVariable("id") Long id, Model model) {
        Concert concert = concertService.getConcertById(id);
        model.addAttribute("concert", concert);
        return "currentConcert";
    }



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

    @PostMapping("/concerts/edit/{id}")
    public String updateConcert(@PathVariable("id") Long id, @ModelAttribute("concert") Concert concert, BindingResult result) {
        if (result.hasErrors()) {
            return "editConcert";
        }
        concert.setId(id);
        concertService.updateConcert(concert);
        return "redirect:/concerts";
    }

    @GetMapping("/api/concerts")
    public ResponseEntity<List<Concert>> getAllConcertsApi() {
        List<Concert> concerts = concertService.getAllConcerts();
        return new ResponseEntity<>(concerts, concerts.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }

    @GetMapping("/api/concerts/{id}")
    public ResponseEntity<Concert> getConcertByIdApi(@PathVariable("id") Long id) {
        Concert concert = concertService.getConcertById(id);
        return new ResponseEntity<>(concert, concert != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/api/concerts/delete/{id}")
    public ResponseEntity<?> deleteConcertApi(@PathVariable("id") Long id) {
        concertService.deleteConcertById(id);
        return ResponseEntity.ok().build();
    }
}
