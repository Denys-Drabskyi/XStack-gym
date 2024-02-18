package org.example.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.TrainingType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainerDto extends UserDto {
  private UUID id;
  private TrainingType specialization;
}
