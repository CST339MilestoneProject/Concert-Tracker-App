package com.gcu.trackerapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import lombok.Data;

/**
 * Represents a concert entity in the application.
 */
@Data
@Table("concerts") // Maps this entity to the "concerts" table in the database
public class Concert {
  @Id // Marks this field as the primary key
  private Long id;

  /**
   * The ID of the user who added the concert.
   */
  private Long userId;

  private String artist; // The artist or band performing at the concert
  private String venue;  // The venue where the concert is held
  private String setlist; // The list of songs performed at the concert
}
