package org.example.dto;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.Past;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class TraineeDto extends UserDto {
  private UUID id;
  @JsonView(Views.PublicTrainee.class)
  @Past(message = "Birthday cannot be in future")
  private LocalDate birthDate;

  @JsonView(Views.PublicTrainee.class)
  private String address;
}
