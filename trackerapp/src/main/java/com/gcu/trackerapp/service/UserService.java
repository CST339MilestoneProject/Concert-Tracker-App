package com.gcu.trackerapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gcu.trackerapp.model.User;
import com.gcu.trackerapp.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public boolean validateUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if(user != null && user.getPassword().equals(password)) {
            return true;
        }

        return false;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
