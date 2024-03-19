package org.example.dto;

import java.util.List;
import javax.validation.constraints.NotNull;
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
