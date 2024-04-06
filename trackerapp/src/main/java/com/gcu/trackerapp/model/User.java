package com.gcu.trackerapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import lombok.Data;

/**
 * Represents a user entity in the application.
 */
@Data
@Table("users") // Maps this entity to the "users" table in the database
public class User {

  @Id // Marks this field as the primary key
  private Long id;

  private String firstName;
  private String lastName;
  private String email;
  private String phoneNumber;
  private String username;
  private String password;

  /**
   * Indicates whether the user is enabled or disabled.
   * Users are enabled by default.
   */
  private boolean enabled = true;
}
