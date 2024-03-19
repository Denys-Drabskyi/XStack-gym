package org.example.dto;

import java.time.LocalDate;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
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
