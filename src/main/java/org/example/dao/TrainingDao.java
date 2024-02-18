package org.example.dao;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.Training;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TrainingDao extends BasicMapDao<UUID, Training>{
  public TrainingDao(Map<UUID, Training> trainingStorage) {
    super(trainingStorage, Training.class.getName());
  }

  public void deleteUserTrainings(UUID id) {
    log.info("Started user:{} trainings delete", id);
    List<Training> userTrainings = storage.values().stream()
        .filter(value -> value.getTraineeId().equals(id) || value.getTrainerId().equals(id))
        .toList();
    userTrainings.forEach(training -> storage.remove(training.getId()));
  }
}
