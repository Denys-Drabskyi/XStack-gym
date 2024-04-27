package org.example.dto;

import java.util.Map;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
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
