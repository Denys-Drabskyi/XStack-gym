package org.example.dto;

import com.fasterxml.jackson.annotation.JsonView;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class TraineeDtoWithTrainers extends TraineeDto {
  // TODO: 3/13/2024 перенести в основну TraineeDto, розставити ігнори на мапперах в операціях з масивами трейні

  @JsonView(Views.PublicTrainee.class)
  private List<TrainerDto> trainers;
}
