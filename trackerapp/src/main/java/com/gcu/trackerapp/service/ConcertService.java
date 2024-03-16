package com.gcu.trackerapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcu.trackerapp.model.Concert;
import com.gcu.trackerapp.repository.ConcertRepository;

@Service
public class ConcertService {

    @Autowired
    private ConcertRepository concertRepository;

    public List<Concert> getAllConcerts() {
        return (List<Concert>) concertRepository.findAll();
    }

    public Concert addConcert(Concert concert) {
        return concertRepository.save(concert);
    }

    public Concert getConcertById(Long id) {
        return concertRepository.findById(id).orElse(null);
    }

    public List<Concert> getConcertsByUserId(Long userId) {
        return concertRepository.findByUserId(userId);
    }

    public Concert addConcertWithUser(Concert concert, Long userId) {
        concert.setUserId(userId);
        return concertRepository.save(concert);
      }

      public void deleteConcertById(Long id) {
        concertRepository.deleteById(id);
    }

    public Concert updateConcert(Concert concert) {
        return concertRepository.save(concert);
    }

}