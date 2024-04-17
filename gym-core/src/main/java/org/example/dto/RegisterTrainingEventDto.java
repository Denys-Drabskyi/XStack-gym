package org.example.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterTrainingEventDto {
  @NonNull
  private String username;
  @NonNull
  private String firstname;
  @NonNull
  private String lastname;
  private boolean isActive;
  @NonNull
  private Date trainingDate;
  private long trainingDuration;
  @NonNull
  private ActionType actionType;
}



