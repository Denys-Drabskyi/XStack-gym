package org.example.entity;

import java.util.UUID;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Trainer extends User {
  private TrainingType specialization;

  @Builder
  public Trainer(UUID id, String firstName, String lastName, String username, String password, boolean isActive,
                 TrainingType specialization) {
    super(id, firstName, lastName, username, password, isActive);
    this.specialization = specialization;
  }
  public static class TrainerBuilder {
    public TrainerBuilder() {}
  }
}
