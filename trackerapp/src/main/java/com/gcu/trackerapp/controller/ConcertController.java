// ConcertController.java
package com.gcu.trackerapp.controller;

import com.gcu.trackerapp.model.Concert;
import com.gcu.trackerapp.service.ConcertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/concerts")
public class ConcertController {

    @Autowired
    private ConcertService concertService;

    @GetMapping
    public String showConcerts(Model model) {
        model.addAttribute("concerts", concertService.getAllConcerts());
        return "concerts";
    }

    @GetMapping("/add")
    public String showAddConcertForm(Model model) {
        model.addAttribute("concert", new Concert());
        return "addConcert";
    }

    @PostMapping("/add")
    public String addConcert(@ModelAttribute("concert") Concert concert, BindingResult result) {
        if (result.hasErrors()) {
            return "addConcert";
        }
        concertService.addConcert(concert);
        return "redirect:/concerts";
    }
}