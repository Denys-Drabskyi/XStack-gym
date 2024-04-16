package org.example.dto;

import java.util.Date;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
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



