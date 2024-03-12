package com.gcu.trackerapp.repository;

import java.util.List;

public interface GenericRepository<T> {

    List<T> findAll();
    T findById(long id);
    boolean create(T object);
    boolean update(T object);
    boolean delete(long id);

}