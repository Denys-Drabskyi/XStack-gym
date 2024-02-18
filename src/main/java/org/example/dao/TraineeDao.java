package org.example.dao;

import java.util.Map;
import java.util.UUID;
import org.example.entity.Trainee;
import org.springframework.stereotype.Component;

@Component
public class TraineeDao extends BasicMapDao<UUID, Trainee> {
  public TraineeDao(Map<UUID, Trainee> traineeStorage) {
    super(traineeStorage, Trainee.class.getName());
  }
}
