package org.example.service.impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.example.dao.TraineeDao;
import org.example.dao.TrainerDao;
import org.example.dao.TrainingDao;
import org.example.dto.TrainingDto;
import org.example.entity.Training;
import org.example.mapper.TrainingMapper;
import org.example.service.TrainingService;
import org.example.service.TrainingTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TrainingServiceImpl implements TrainingService {
  @Autowired
  private TrainingDao trainingDao;
  @Autowired
  private TrainerDao trainerDao;
  @Autowired
  private TraineeDao traineeDao;
  @Autowired
  private TrainingMapper trainingMapper;
  @Autowired
  private TrainingTypeService trainingTypeService;

  @Override
  public TrainingDto create(TrainingDto dto) {
    log.info("Creating new training for trainee with username:{} with trainer:{}", dto.getTraineeUsername(), dto.getTrainerUsername());
    Training training = trainingMapper.toEntity(dto);
    training.setTrainee(traineeDao.getByUsername(dto.getTraineeUsername()));
    training.setTrainer(trainerDao.getByUsername(dto.getTrainerUsername()));
    training.setType(trainingTypeService.getByName(dto.getTrainingType()));
    training = trainingDao.save(training);
    log.info("Created new training for trainee with username:{} with trainer:{}", dto.getTraineeUsername(), dto.getTrainerUsername());
    return trainingMapper.toDto(training);
  }

  @Override
  public List<TrainingDto> getTraineeTrainingListByTrainerAndDateBetween
      (String traineeUsername, Collection<String> trainerUsernames, Date from, Date to) {
    log.info("Getting trainee with username:{} trainings list", traineeUsername);
    return trainingMapper.toDtoList(trainingDao.getTraineeTrainingListByTrainerAndDateBetween(traineeUsername, trainerUsernames, from, to));
  }

  @Override
  public List<TrainingDto> getTrainerTrainingListByTraineeAndDateBetween
      (String trainerUsername, Collection<String> traineeUsernames, Date from, Date to) {
    log.info("Getting trainer with username:{} trainees list", trainerUsername);
    return trainingMapper.toDtoList(trainingDao.getTrainerTrainingListByTraineeAndDateBetween(trainerUsername, traineeUsernames, from, to));
  }

  @Override
  public double getAverageTrainerTrainingsCount() {
    return trainerDao.findAll().stream()
        .map(trainer ->  trainingDao.countTrainerTrainings(trainer.getUser().getUsername()))
        .mapToInt(Integer::intValue)
        .average()
        .orElse(0.0);
  }
}