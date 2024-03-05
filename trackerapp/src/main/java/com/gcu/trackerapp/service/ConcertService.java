package com.gcu.trackerapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gcu.trackerapp.model.Concert;

@Service
public class ConcertService {
    private final List<Concert> concerts = new ArrayList<>();

    public List<Concert> getAllConcerts(){
        return concerts;
    }

    public void addConcert(Concert concert) {

        concerts.add(concert);
    }

}