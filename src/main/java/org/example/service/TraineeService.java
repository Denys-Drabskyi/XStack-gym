package org.example.service;

import java.util.UUID;
import org.example.dto.TraineeDto;
import org.example.entity.Trainee;

public interface TraineeService {
  TraineeDto getById(UUID id);
  Trainee create(Trainee trainee);
  TraineeDto create(TraineeDto traineeDto);
  Trainee update(Trainee trainee);
  TraineeDto update(TraineeDto traineeDto);
  void deleteById(UUID id);
}
