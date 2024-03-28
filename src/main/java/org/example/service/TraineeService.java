package org.example.service;

import java.util.UUID;
import org.example.dto.TraineeDto;
import org.example.dto.TraineeDtoWithTrainers;
import org.example.dto.UserCredentialsDto;

public interface TraineeService {
  boolean existsById(UUID id);

  TraineeDto get(UserCredentialsDto credentials);

  TraineeDtoWithTrainers getWithTrainers(String username);

  TraineeDto create(TraineeDto dto);

  TraineeDtoWithTrainers update(TraineeDto dto);

  void deleteByUsername(UserCredentialsDto credentials);
}
