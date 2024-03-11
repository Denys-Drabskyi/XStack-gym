package org.example.service.impl;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.example.aop.Auth;
import org.example.dao.TraineeDao;
import org.example.dao.TrainerDao;
import org.example.dto.TrainerDto;
import org.example.dto.UserCredentialsDto;
import org.example.entity.Trainee;
import org.example.entity.Trainer;
import org.example.exception.EntityNotFoundException;
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
  public boolean existsById(UUID id) {
    return trainerDao.existById(id);
  }

  @Auth
  @Override
  public TrainerDto get(UserCredentialsDto credentials) {
    return trainerMapper.toDto(trainerDao.getByUsername(credentials.getUsername())
        .orElseThrow(() -> EntityNotFoundException.byUsername(credentials.getUsername(), Trainer.class.getSimpleName()))
    );
  }

  @Override
  @Transactional
  public TrainerDto create(TrainerDto dto) {
    return trainerMapper.toDto(trainerDao.save(createTrainer(dto)));
  }

  @Auth
  @Override
  public TrainerDto update(TrainerDto dto) {
    Optional<Trainer> storedTrainer = trainerDao.getByUsername(dto.getUsername());
    if (storedTrainer.isEmpty()){
      throw EntityNotFoundException.byUsername(dto.getUsername(), Trainer.class.getSimpleName());
    }
    updateTrainer(dto, storedTrainer.get());
    return trainerMapper.toDto(trainerDao.save(storedTrainer.get()));
  }

  @Auth
  @Override
  public void addTrainerToTrainee(UserCredentialsDto traineeCredentials, String trainerUsername) {
    Trainee trainee = traineeDao.getByUsername(traineeCredentials.getUsername())
        .orElseThrow(() -> EntityNotFoundException.byUsername(traineeCredentials.getUsername(), Trainee.class.getSimpleName()));
    Trainer trainer = trainerDao.getByUsername(trainerUsername)
        .orElseThrow(() -> EntityNotFoundException.byUsername(trainerUsername, Trainer.class.getSimpleName()));
    trainee.getTrainers().add(trainer);
    traineeDao.save(trainee);
  }

  @Auth
  @Override
  public List<TrainerDto> getTrainersNotAssignedToTrainee(UserCredentialsDto traineeCredentials) {
    log.info("Seeking trainers, not assigned to user:{}", traineeCredentials.getUsername());
    Trainee trainee = traineeDao.getByUsername(traineeCredentials.getUsername())
        .orElseThrow(() -> EntityNotFoundException.byUsername(traineeCredentials.getUsername(), Trainee.class.getSimpleName()));
    return trainerMapper.toDtoList(trainerDao.getTrainersNotAssignedToTrainee(trainee));
  }

  private void updateTrainer(TrainerDto dto, Trainer entity) {
    log.info("Updating saved trainer from dto");
    trainerMapper.updateEntityFromDto(dto, entity);
    entity.setSpecializations(trainingTypeService.getByName(dto.getSpecializations()));
    userService.update(dto, entity.getUser());
  }

  private Trainer createTrainer(TrainerDto dto) {
    log.info("Crating new trainer");
    return trainerMapper.toBuilder(dto)
        .user(userService.createUser(dto))
        .specializations(trainingTypeService.getByName(dto.getSpecializations()))
        .build();
  }
}
