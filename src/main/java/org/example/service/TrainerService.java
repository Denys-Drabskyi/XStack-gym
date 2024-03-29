package org.example.service;

import java.util.List;
import java.util.UUID;
import org.example.dto.TrainerDto;
import org.example.dto.TrainerDtoWithTrainees;
import org.example.dto.UpdateTrainersListDto;
import org.example.dto.UserCredentialsDto;

public interface TrainerService {
  boolean existsById(UUID id);

  TrainerDto get(UserCredentialsDto credentials);

  TrainerDtoWithTrainees getByUsername(String username);

  TrainerDto create(TrainerDto trainer);

  TrainerDtoWithTrainees update(TrainerDto trainer);

  void addTrainerToTrainee(UserCredentialsDto traineeCredentials, String trainerUsername);

  List<TrainerDto> getTrainersNotAssignedToTrainee(String username);

  List<TrainerDto> updateTrainers(UpdateTrainersListDto dto);
}
