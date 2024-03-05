package org.example.service;

import java.util.UUID;
import org.example.dto.TraineeDto;
import org.example.dto.UserCredentialsDto;

public interface TraineeService {
  boolean existsById(UUID id);

  TraineeDto get(UserCredentialsDto credentials);

  TraineeDto create(TraineeDto dto);

  TraineeDto update(TraineeDto dto);

  void deleteByUsername(UserCredentialsDto credentials);
}
