package com.gcu.trackerapp.controller;

import com.gcu.trackerapp.model.Concert;
import com.gcu.trackerapp.service.ConcertService;
import com.gcu.trackerapp.service.UserService;

import javax.servlet.http.HttpSession;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/concerts")
public class ConcertController {

    @Autowired
    private ConcertService concertService;


    @Autowired
    private UserService userService;

    @GetMapping
    public String showConcerts(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Long userId = userService.findByUsername(username).getId();
        List<Concert> userConcerts = concertService.getConcertsByUserId(userId);
        model.addAttribute("concerts", userConcerts);
        model.addAttribute("userId", userId);
        return "concerts";
    }


    @PostMapping("/add")
    public String addConcert(@ModelAttribute("concert") Concert concert, BindingResult result, @RequestParam("userId") Long userId) {
        if (result.hasErrors()) {
            return "addConcert";
        }
        concertService.addConcertWithUser(concert, userId);
        return "redirect:/concerts";
    }

    @GetMapping("/{id}")
    public String showConcertDetails(@PathVariable("id") Long id, Model model) {
    Concert concert = concertService.getConcertById(id);
    model.addAttribute("concert", concert);
    return "currentConcert";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteConcert(@PathVariable("id") Long id) {
        concertService.deleteConcertById(id);
        return "redirect:/concerts";
    }

    @GetMapping("/edit/{id}")
    public String showEditConcertForm(@PathVariable("id") Long id, Model model) {
        Concert concert = concertService.getConcertById(id);
        if (concert != null) {
            model.addAttribute("concert", concert);
            return "editConcert";
        } else {
            return "redirect:/concerts";
        }
}

@PostMapping("/edit/{id}")
public String updateConcert(@PathVariable("id") Long id, @ModelAttribute("concert") Concert concert, BindingResult result, Model model) {
    if (result.hasErrors()) {
        return "editConcert";
    }
    concert.setId(id);
    concertService.updateConcert(concert);
    return "redirect:/concerts";
}
}