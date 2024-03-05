package org.example.dao;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.Training;
import org.example.repository.TrainingRepository;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TrainingDao extends BasicDao<UUID, Training, TrainingRepository> {
  public TrainingDao(TrainingRepository repository) {
    super(repository, Training.class.getSimpleName());
  }

  public List<Training> getTraineeTrainingListByTrainerAndDateBetween
      (String traineeUsername, Collection<String> trainerUsernames, Date from, Date to) {
    log.info("Started {} trainings with filter searching", traineeUsername);
    return repository.getTraineeTrainingListByTrainerAndDateBetween(traineeUsername, trainerUsernames, from, to);
  }
  public List<Training> getTrainerTrainingListByTraineeAndDateBetween
      (String trainerUsername, Collection<String> traineeUsernames, Date from, Date to) {
    log.info("Started {} trainings with filter searching", trainerUsername);
    return repository.getTrainerTrainingListByTraineeAndDateBetween(trainerUsername, traineeUsernames, from, to);
  }
}
