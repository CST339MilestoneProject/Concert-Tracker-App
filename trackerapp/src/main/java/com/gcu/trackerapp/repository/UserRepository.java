package com.gcu.trackerapp.repository;

import org.springframework.data.repository.CrudRepository;
import com.gcu.trackerapp.model.User;

/**
 * Repository interface for {@link User} entity operations.
 */
public interface UserRepository extends CrudRepository<User, Long> {

  /**
   * Retrieves a {@link User} entity by its username.
   *
   * @param username The username of the user.
   * @return The {@link User} entity or null if not found.
   */
  User findByUsername(String username);
}
