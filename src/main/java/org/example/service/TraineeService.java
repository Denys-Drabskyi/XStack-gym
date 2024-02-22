package org.example.service;

import java.util.Optional;
import java.util.UUID;
import org.example.entity.Trainee;

public interface TraineeService {
  Optional<Trainee> getById(UUID id);
  Trainee getExistingById(UUID id);
  Trainee create(Trainee trainee);
  Trainee update(Trainee trainee);
  void deleteById(UUID id);
}
