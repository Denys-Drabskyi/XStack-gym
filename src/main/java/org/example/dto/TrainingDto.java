package org.example.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainingDto implements ValidatedDto {
  @NotBlank(message = "Trainee username name must not be blank")
  private String traineeUsername;
  @NotBlank(message = "Trainer username name must not be blank")
  private String trainerUsername;
  @NotBlank(message = "Name name must not be blank")
  private String name;
  @NotBlank(message = "Training type name must not be blank")
  private String trainingType;
  @NotNull(message = "Date must be present")
  @Future(message = "Date must be in future")
  private LocalDate date;
  @Positive(message = "Invalid training duration")
  private long duration;
}
