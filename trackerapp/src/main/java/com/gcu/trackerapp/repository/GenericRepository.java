package com.gcu.trackerapp.repository;

import java.util.List;

/**
 * Generic repository interface providing basic CRUD operations.
 *
 * @param <T> The entity type this repository manages.
 */
public interface GenericRepository<T> {

  /**
   * Retrieves all entities of type T.
   *
   * @return A list of entities of type T.
   */
  List<T> findAll();

  /**
   * Finds an entity by its ID.
   *
   * @param id The ID of the entity.
   * @return The found entity or null if not found.
   */
  T findById(long id);

  /**
   * Creates a new entity.
   *
   * @param object The entity to be created.
   * @return true if the creation was successful.
   */
  boolean create(T object);

  /**
   * Updates an existing entity.
   *
   * @param object The entity to be updated.
   * @return true if the update was successful.
   */
  boolean update(T object);

  /**
   * Deletes an entity by its ID.
   *
   * @param id The ID of the entity to be deleted.
   * @return true if the deletion was successful.
   */
  boolean delete(long id);
}
