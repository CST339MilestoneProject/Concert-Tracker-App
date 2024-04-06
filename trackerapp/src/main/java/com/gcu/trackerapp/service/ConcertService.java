package com.gcu.trackerapp.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gcu.trackerapp.model.Concert;
import com.gcu.trackerapp.repository.ConcertRepository;

/**
 * Service class for managing concert-related operations.
 */
@Service
public class ConcertService {

    @Autowired
    private ConcertRepository concertRepository;

    /**
     * Retrieves all concerts.
     *
     * @return A list of {@link Concert} entities.
     */
    public List<Concert> getAllConcerts() {
        return (List<Concert>) concertRepository.findAll();
    }

    /**
     * Adds a new concert.
     *
     * @param concert The {@link Concert} entity to add.
     * @return The added {@link Concert} entity.
     */
    public Concert addConcert(Concert concert) {
        return concertRepository.save(concert);
    }

    /**
     * Retrieves a concert by its ID.
     *
     * @param id The ID of the concert.
     * @return The {@link Concert} entity, or null if not found.
     */
    public Concert getConcertById(Long id) {
        return concertRepository.findById(id).orElse(null);
    }

    /**
     * Retrieves concerts by a user's ID.
     *
     * @param userId The ID of the user.
     * @return A list of {@link Concert} entities.
     */
    public List<Concert> getConcertsByUserId(Long userId) {
        return concertRepository.findByUserId(userId);
    }

    /**
     * Adds a concert associated with a specific user.
     *
     * @param concert The {@link Concert} entity to add.
     * @param userId The ID of the user.
     * @return The added {@link Concert} entity.
     */
    public Concert addConcertWithUser(Concert concert, Long userId) {
        concert.setUserId(userId);
        return concertRepository.save(concert);
    }

    /**
     * Deletes a concert by its ID.
     *
     * @param id The ID of the concert to delete.
     */
    public void deleteConcertById(Long id) {
        concertRepository.deleteById(id);
    }

    /**
     * Updates the details of an existing concert.
     *
     * @param concert The {@link Concert} entity with updated details.
     * @return The updated {@link Concert} entity.
     */
    public Concert updateConcert(Concert concert) {
        return concertRepository.save(concert);
    }
}
