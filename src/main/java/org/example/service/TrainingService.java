package org.example.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.example.dto.TrainingDto;
import org.example.dto.UserCredentialsDto;
import org.example.entity.Training;

public interface TrainingService {
  TrainingDto create(TrainingDto training);

  List<Training> getTraineeTrainingListByTrainerAndDateBetween
      (UserCredentialsDto traineeCredentials, Collection<String> trainerUsernames, Date from, Date to);

  List<Training> getTrainerTrainingListByTraineeAndDateBetween
      (UserCredentialsDto trainerCredentials, Collection<String> traineeUsernames, Date from, Date to);
}
