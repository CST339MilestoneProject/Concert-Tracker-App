package com.gcu.trackerapp.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.gcu.trackerapp.model.Concert;

/**
 * Repository interface for {@link Concert} entity operations.
 */
public interface ConcertRepository extends CrudRepository<Concert, Long> {

  /**
   * Retrieves a list of concerts based on the user ID.
   *
   * @param userId The ID of the user whose concerts are to be fetched.
   * @return A list of {@link Concert} entities.
   */
  List<Concert> findByUserId(Long userId);
}
