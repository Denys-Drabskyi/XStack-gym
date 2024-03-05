package org.example.service;

import java.util.List;
import java.util.UUID;
import org.example.dto.TrainerDto;
import org.example.dto.UserCredentialsDto;
import org.example.entity.Trainer;

public interface TrainerService {
  boolean existsById(UUID id);

  TrainerDto get(UserCredentialsDto credentials);
  TrainerDto create(TrainerDto trainer);

  TrainerDto update(TrainerDto trainer);

  void addTrainerToTrainee(String trainerUsername, UserCredentialsDto traineeCredentials);

  List<Trainer> getTrainersNotAssignedToTrainee(UserCredentialsDto credentials);
}
