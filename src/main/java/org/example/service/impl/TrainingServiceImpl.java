package org.example.service.impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.example.dao.TrainingDao;
import org.example.dto.TraineeDto;
import org.example.dto.TrainerDto;
import org.example.dto.TrainingDto;
import org.example.entity.Training;
import org.example.entity.TrainingType;
import org.example.exception.EntityNotFoundException;
import org.example.mapper.TrainingMapper;
import org.example.service.TraineeService;
import org.example.service.TrainerService;
import org.example.service.TrainingService;
import org.example.service.TrainingTypeService;
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
  @Autowired
  private TrainingMapper trainingMapper;
  @Autowired
  private TrainingTypeService trainingTypeService;

  @Override
  public TrainingDto create(TrainingDto dto) {
    TraineeDto traineeDto = traineeService.getByUsername(dto.getTraineeUsername());
    TrainerDto trainerDto = trainerService.getByUsername(dto.getTrainerUsername());
    Training training = trainingMapper.toEntity(dto);
    training.getTrainee().setId(traineeDto.getId());
    training.getTrainer().setId(trainerDto.getId());
    TrainingType trainingType = trainingTypeService.getByName(dto.getTrainingType());
    if (trainingType == null) {
      throw EntityNotFoundException.types(List.of(dto.getTrainingType()));
    }
    training.setType(trainingType);
    return trainingMapper.toDto(trainingDao.save(training));
  }

  @Override
  public List<TrainingDto> getTraineeTrainingListByTrainerAndDateBetween
      (String traineeUsername, Collection<String> trainerUsernames, Date from, Date to) {
    return trainingMapper.toDtoList(trainingDao.getTraineeTrainingListByTrainerAndDateBetween(traineeUsername, trainerUsernames, from, to));
  }

  @Override
  public List<TrainingDto> getTrainerTrainingListByTraineeAndDateBetween
      (String trainerUsername, Collection<String> traineeUsernames, Date from, Date to) {
    return trainingMapper.toDtoList(trainingDao.getTrainerTrainingListByTraineeAndDateBetween(trainerUsername, traineeUsernames, from, to));
  }
}
