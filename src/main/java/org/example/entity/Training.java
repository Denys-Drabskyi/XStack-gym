package org.example.entity;

import java.util.Date;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Training implements Entity<UUID> {
  private UUID id;
  private UUID traineeId;
  private UUID trainerId;
  private String name;
  private TrainingType type;
  private Date date;
  private long duration;

  @Override
  public UUID getId() {
    return this.id;
  }

  public static class TrainingBuilder {
    public TrainingBuilder() {}
  }
}
