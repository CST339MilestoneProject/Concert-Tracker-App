package com.gcu.trackerapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.gcu.trackerapp.model.Concert;

public interface ConcertRepository extends CrudRepository<Concert, Long> {
}