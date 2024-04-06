package com.gcu.trackerapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.gcu.trackerapp.model.User;
import com.gcu.trackerapp.repository.UserRepository;

/**
 * Service class for managing user-related operations.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Registers a new user by saving it in the repository.
     *
     * @param user The {@link User} entity to be registered.
     * @return The registered {@link User} entity with encoded password.
     */
    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        return userRepository.save(user);
    }

    /**
     * Finds a user by username.
     *
     * @param username The username of the user.
     * @return The found {@link User} entity or null if not found.
     */
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
