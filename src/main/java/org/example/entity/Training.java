package org.example.entity;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;
import lombok.Data;

@Data
public class Training implements Entity<UUID> {
  private final UUID id;
  private final UUID traineeId;
  private final UUID trainerId;
  private String name;
  private TrainingType type;
  private Date date;
  private long duration;

  public Training(UUID traineeId, UUID trainerId, String name, TrainingType type, Date date, long duration) {
    Objects.requireNonNull(traineeId, "traineeId date can not be null");
    Objects.requireNonNull(trainerId, "trainerId date can not be null");
    Objects.requireNonNull(name, "name date can not be null");
    Objects.requireNonNull(type, "type date can not be null");
    Objects.requireNonNull(date, "date date can not be null");
    this.id = UUID.randomUUID();
    this.traineeId = traineeId;
    this.trainerId = trainerId;
    this.name = name;
    this.type = type;
    this.date = date;
    this.duration = duration;
  }

  @Override
  public UUID getId() {
    return this.id;
  }
}
