package org.example.dao;

import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.Trainee;
import org.example.entity.Trainer;
import org.example.exception.EntityNotFoundException;
import org.example.repository.TrainerRepository;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TrainerDao extends BasicDao<UUID, Trainer, TrainerRepository> {
  public TrainerDao(TrainerRepository repository) {
    super(repository, Trainer.class.getSimpleName());
  }

  public List<Trainer> findAll(){
    return repository.findAll();
  }

  public Trainer getByUsername(String username) {
    log.info("Looking for trainer with username:{}", username);
    return repository.getByUserUsername(username)
        .orElseThrow(() -> EntityNotFoundException.byUsername(username, Trainer.class.getSimpleName()));
  }
  public List<Trainer> getByUsernameIn(List<String> usernames) {
    log.info("Looking for trainers from list");
    log.debug(String.valueOf(usernames));
    return repository.getTrainersByUser_UsernameIn(usernames);
  }

  public List<Trainer> getTrainersNotAssignedToTrainee(Trainee trainee) {
    log.info("Looking for trainers, not assigned to trainee with username:{}", trainee.getUser().getUsername());
    return repository.getTrainersNotAssignedToTrainee(trainee);
  }


}
