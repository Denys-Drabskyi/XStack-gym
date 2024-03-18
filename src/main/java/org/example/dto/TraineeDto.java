package org.example.dto;

import java.time.LocalDate;
import java.util.UUID;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class TraineeDto extends UserDto {
  private UUID id;
  private LocalDate birthDate;
  private String address;
}
