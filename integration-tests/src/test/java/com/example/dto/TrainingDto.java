package com.example.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainingDto {
  private String traineeUsername;
  private String trainerUsername;
  private String name;
  private String trainingType;
  private String date;
  private long duration;
}
