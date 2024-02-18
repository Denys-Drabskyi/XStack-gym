package org.example.service.impl;

import java.util.UUID;
import org.example.dao.TrainingDao;
import org.example.entity.Training;
import org.example.exception.EntityNotFoundException;
import org.example.service.TraineeService;
import org.example.service.TrainerService;
import org.example.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainingServiceImpl implements TrainingService {

  @Autowired
  private TrainingDao trainingDao;
  @Autowired
  private TrainerService trainerService;
  @Autowired
  private TraineeService traineeService;

  @Override
  public Training getById(UUID id) {
    return trainingDao.get(id).orElseThrow(
        () -> EntityNotFoundException.byId(id, Training.class.getSimpleName())
    );
  }

  @Override
  public Training create(Training training) {
    traineeService.getById(training.getTraineeId());
    trainerService.getById(training.getTrainerId());
    return trainingDao.save(training);
  }
}
