package org.example.service.impl;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.example.dao.TraineeDao;
import org.example.dao.TrainerDao;
import org.example.dto.TrainerDto;
import org.example.dto.TrainerDtoWithTrainees;
import org.example.dto.UpdateTrainersListDto;
import org.example.dto.UserCredentialsDto;
import org.example.entity.Trainee;
import org.example.entity.Trainer;
import org.example.mapper.TrainerMapper;
import org.example.service.TrainerService;
import org.example.service.TrainingTypeService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TrainerServiceImpl implements TrainerService {
  @Autowired
  private TrainerDao trainerDao;
  @Autowired
  private TrainerMapper trainerMapper;
  @Autowired
  private TraineeDao traineeDao;
  @Autowired
  private UserService userService;
  @Autowired
  private TrainingTypeService trainingTypeService;

  @Override
  public TrainerDtoWithTrainees getByUsername(String username) {
    return trainerMapper.toDtoWithTrainees(trainerDao.getByUsername(username));
  }

  @Override
  @Transactional
  public TrainerDto create(TrainerDto dto) {
    log.info("Started new trainer creation");
    Trainer rez = trainerMapper.toBuilder(dto)
        .user(userService.createUser(dto))
        .specialization(trainingTypeService.getByName(dto.getSpecialization()))
        .build();
    rez = trainerDao.save(rez);
    log.info("Created new trainer with username:{}", rez.getUser().getUsername());
    return trainerMapper.toDto(rez);
  }

  @Override
  public TrainerDtoWithTrainees update(TrainerDto dto) {
    Trainer storedTrainer = trainerDao.getByUsername(dto.getUsername());

    log.info("Updating existing trainer with username:{} from dto", dto.getUsername());
    trainerMapper.updateEntityFromDto(dto, storedTrainer);
    storedTrainer.setSpecialization(trainingTypeService.getByName(dto.getSpecialization()));
    userService.update(dto, storedTrainer.getUser());
    trainerDao.save(storedTrainer);
    log.info("Updated existing trainer with username:{}", dto.getUsername());

    return trainerMapper.toDtoWithTrainees(storedTrainer);
  }

  @Override
  public List<TrainerDto> getTrainersNotAssignedToTrainee(String username) {
    Trainee trainee = traineeDao.getByUsername(username);
    return trainerMapper.toDtoList(trainerDao.getTrainersNotAssignedToTrainee(trainee));
  }

  @Override
  public List<TrainerDto> updateTrainers(String traineeUsername, UpdateTrainersListDto dto) {
    Trainee trainee = traineeDao.getByUsername(traineeUsername);
    log.info("Started trainee with username:{} trainers update", traineeUsername);
    List<Trainer> trainers = trainerDao.getByUsernameIn(dto.getTrainers());
    trainee.setTrainers(trainers);
    traineeDao.save(trainee);
    log.info("Updated trainee with username:{} trainers", traineeUsername);
    return trainerMapper.toDtoList(trainee.getTrainers());
  }
}
