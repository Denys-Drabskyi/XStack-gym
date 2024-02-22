package org.example.entity;

import java.util.Objects;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Trainer extends User {
  private TrainingType specialization;

  @Builder
  public Trainer(String firstName, String lastName, TrainingType specialization) {
    super(firstName, lastName);
    Objects.requireNonNull(specialization, "specialization can not be null");
    this.specialization = specialization;
  }

  public void setSpecialization(TrainingType specialization) {
    Objects.requireNonNull(specialization, "Specialization can not be null");
    this.specialization = specialization;
  }
}
