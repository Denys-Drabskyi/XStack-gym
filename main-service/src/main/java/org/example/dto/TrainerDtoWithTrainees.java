package org.example.dto;

import com.fasterxml.jackson.annotation.JsonView;
import java.util.List;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class TrainerDtoWithTrainees extends TrainerDto {
  // TODO: 3/13/2024 перенести в основну TrainerDto, розставити ігнори на мапперах в операціях з масивами тренерів 
  
  @JsonView(Views.PublicTrainer.class)
  private List<TraineeDto> trainees;
}
