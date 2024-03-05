package org.example.service.impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.example.aop.Auth;
import org.example.dao.TrainingDao;
import org.example.dto.TrainingDto;
import org.example.dto.UserCredentialsDto;
import org.example.entity.Trainee;
import org.example.entity.Trainer;
import org.example.entity.Training;
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
    if (traineeService.existsById(dto.getTraineeDto().getId())){
      throw EntityNotFoundException.byId(dto.getTraineeDto().getId(), Trainee.class.getSimpleName());
    }

    if (trainerService.existsById(dto.getTrainerDto().getId())){
      throw EntityNotFoundException.byId(dto.getTraineeDto().getId(), Trainer.class.getSimpleName());
    }

    Training training = trainingMapper.toEntity(dto);
    training.setType(trainingTypeService.getByName(dto.getTrainingType()));
    return trainingMapper.toDto(trainingDao.save(training));
  }

  @Auth
  @Override
  public List<Training> getTraineeTrainingListByTrainerAndDateBetween
        (UserCredentialsDto traineeCredentials, Collection<String> trainerUsernames, Date from, Date to) {
    return trainingDao.getTraineeTrainingListByTrainerAndDateBetween(traineeCredentials.getUsername(), trainerUsernames, from, to);
  }

  @Auth
  @Override
  public List<Training> getTrainerTrainingListByTraineeAndDateBetween
      (UserCredentialsDto trainerCredentials, Collection<String> traineeUsernames, Date from, Date to) {
    return trainingDao.getTrainerTrainingListByTraineeAndDateBetween(trainerCredentials.getUsername(), traineeUsernames, from, to);
  }
}
