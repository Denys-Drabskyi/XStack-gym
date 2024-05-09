package com.example.trainersservice.entity;

import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document("training-summary")
public class TrainingSummary {
  @Id
  private UUID id;
  private String username;
  @Indexed
  private String firstname;
  @Indexed
  private String lastname;
  private boolean isActive;
  private List<TrainingYear> years;
}
