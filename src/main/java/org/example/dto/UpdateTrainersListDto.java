package org.example.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class UpdateTrainersListDto extends UserCredentialsDto {
  @NotNull
  private List<String> trainers;
}
