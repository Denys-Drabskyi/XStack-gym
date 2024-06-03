package org.example.dto;

import com.fasterxml.jackson.annotation.JsonView;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class TrainerDtoWithTrainees extends TrainerDto {
  // TODO: 3/13/2024 перенести в основну TrainerDto, розставити ігнори на мапперах в операціях з масивами тренерів 

  @JsonView(Views.PublicTrainer.class)
  private List<TraineeDto> trainees;
}
