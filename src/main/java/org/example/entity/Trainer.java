package org.example.entity;

import java.util.Objects;
import java.util.UUID;
import lombok.Data;

@Data
public class Trainer implements Entity<UUID> {
  private final UUID id;
  private TrainingType specialization;
  private final UUID userId;

  public Trainer(TrainingType specialization, UUID userId) {
    Objects.requireNonNull(specialization, "specialization can not be null");
    Objects.requireNonNull(userId, "userId date can not be null");
    this.id = UUID.randomUUID();
    this.specialization = specialization;
    this.userId = userId;
  }

  @Override
  public UUID getId() {
    return this.id;
  }
}
