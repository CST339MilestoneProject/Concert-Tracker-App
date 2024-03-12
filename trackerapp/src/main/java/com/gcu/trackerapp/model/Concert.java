package com.gcu.trackerapp.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table("concerts")
public class Concert {

  @Id
  private Long id;
  private String artist;
  private String venue;
  private String setlist;
}