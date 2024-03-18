package org.example.dao;

import java.util.Optional;
import java.util.UUID;
import org.example.entity.Trainee;
import org.example.repository.TraineeRepository;
import org.springframework.stereotype.Component;

@Component
public class TraineeDao extends BasicDao<UUID, Trainee, TraineeRepository> {
  public TraineeDao(TraineeRepository traineeRepository) {
    super(traineeRepository, Trainee.class.getName());
  }

  public Optional<Trainee> getByUsername(String username) {
    return repository.getByUserUsername(username);
  }
}
