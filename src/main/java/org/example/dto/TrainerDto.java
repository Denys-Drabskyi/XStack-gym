package org.example.dto;

import java.util.List;
import java.util.UUID;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class TrainerDto extends UserDto {
  private UUID id;
  private List<String> specializations;
}
