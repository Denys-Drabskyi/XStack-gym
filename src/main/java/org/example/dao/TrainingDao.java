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

  private final String message = """
      Started {} trainings with:
        date from:\t{},
        date to:  \t{},
        {}: \t{}
      """;

  public List<Training> getTraineeTrainingListByTrainerAndDateBetween
      (String traineeUsername, Collection<String> trainerUsernames, Date from, Date to) {
    log.debug(message, traineeUsername, from, to, "trainers", trainerUsernames);
    return repository.getTraineeTrainingListByTrainerAndDateBetween(traineeUsername, trainerUsernames, from, to);
  }
  public List<Training> getTrainerTrainingListByTraineeAndDateBetween
      (String trainerUsername, Collection<String> traineeUsernames, Date from, Date to) {
    log.debug(message, trainerUsername, from, to, "trainees", traineeUsernames);
    return repository.getTrainerTrainingListByTraineeAndDateBetween(trainerUsername, traineeUsernames, from, to);
  }

  public int countTrainerTrainings(String trainerUsername) {
    return repository.countAllByTrainerUserUsername(trainerUsername);
  }
}
