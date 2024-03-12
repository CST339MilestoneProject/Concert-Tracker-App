package com.gcu.trackerapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.gcu.trackerapp.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
