package org.example.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.example.dto.TrainingDto;

public interface TrainingService {
  TrainingDto create(TrainingDto training);

  List<TrainingDto> getTraineeTrainingListByTrainerAndDateBetween
      (String traineeUsername, Collection<String> trainerUsernames, Date from, Date to);

  List<TrainingDto> getTrainerTrainingListByTraineeAndDateBetween
      (String trainerUsername, Collection<String> traineeUsernames, Date from, Date to);
}
