package com.gcu.trackerapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.gcu.trackerapp.model.Concert;

public interface ConcertRepository extends CrudRepository<Concert, Long> {
  List<Concert> findByUserId(Long userId);
}