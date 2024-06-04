package com.example.dto;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrainerStatisticsDto {
  @NonNull
  private String username;
  @NonNull
  private String firstname;
  @NonNull
  private String lastname;
  private boolean isActive;
  @NonNull
  private Map<Integer, Map<Integer, Long>> duration;
}
