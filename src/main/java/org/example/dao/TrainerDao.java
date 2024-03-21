package org.example.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.example.entity.Trainee;
import org.example.entity.Trainer;
import org.example.repository.TrainerRepository;
import org.springframework.stereotype.Component;

@Component
public class TrainerDao extends BasicDao<UUID, Trainer, TrainerRepository> {
  public TrainerDao(TrainerRepository repository) {
    super(repository, Trainer.class.getSimpleName());
  }
  public Optional<Trainer> getByUsername(String username) {
    return repository.getByUserUsername(username);
  }
  public List<Trainer> getByUsernameIn(List<String> usernames) {
    return repository.getTrainersByUser_UsernameIn(usernames);
  }

  public List<Trainer> getTrainersNotAssignedToTrainee(Trainee trainee) {
    return repository.getTrainersNotAssignedToTrainee(trainee);
  }
}
