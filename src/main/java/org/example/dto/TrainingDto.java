package org.example.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.Date;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TrainingDto {
  private UUID trainingId;
  private TraineeDto traineeDto;
  private TrainerDto trainerDto;
  @NotBlank(message = "Training name must not be blank")
  private String name;
  private String trainingType;
  @NotNull(message = "Date must be present")
  @Future(message = "Date must be in future")
  private Date date;
  @Positive(message = "Invalid training duration")
  private long duration;
}
