package org.example.dao;

import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.Trainee;
import org.example.exception.EntityNotFoundException;
import org.example.repository.TraineeRepository;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TraineeDao extends BasicDao<UUID, Trainee, TraineeRepository> {
  public TraineeDao(TraineeRepository traineeRepository) {
    super(traineeRepository, Trainee.class.getName());
  }

  public Trainee getByUsername(String username) {
    log.info("Looking for trainee with username:{}", username);
    return repository.getByUserUsername(username).
        orElseThrow(() -> EntityNotFoundException.byUsername(username, Trainee.class.getSimpleName()));
  }
}
