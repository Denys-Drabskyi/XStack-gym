package org.example.repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.example.entity.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingRepository extends JpaRepository<Training, UUID> {
  List<Training> getByTraineeUserUsernameAndTrainerUserUsernameInAndDateBetween(String trainee_user_username,
                                                                                Collection<String> trainer_user_username,
                                                                                Date date, Date date2);

  List<Training> getByTrainerUserUsernameAndTraineeUserUsernameInAndDateBetween(String trainer_user_username,
                                                                                Collection<String> trainee_user_username,
                                                                                Date date, Date date2);

  int countAllByTrainerUserUsername(String trainerUsername);
  default List<Training> getTraineeTrainingListByTrainerAndDateBetween
      (String traineeUsername, Collection<String> trainerUsernames, Date from, Date to) {
    return getByTraineeUserUsernameAndTrainerUserUsernameInAndDateBetween(traineeUsername, trainerUsernames, from, to);
  }

  default List<Training> getTrainerTrainingListByTraineeAndDateBetween
      (String trainerUsername, Collection<String> traineeUsernames, Date from, Date to) {
    return getByTrainerUserUsernameAndTraineeUserUsernameInAndDateBetween(trainerUsername, traineeUsernames, from, to);
  }
}
