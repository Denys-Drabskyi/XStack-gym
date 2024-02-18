package org.example.service.impl;

import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.example.dao.TrainerDao;
import org.example.dto.TrainerDto;
import org.example.entity.Trainer;
import org.example.entity.User;
import org.example.exception.EntityNotFoundException;
import org.example.mapper.TrainerMapper;
import org.example.service.TrainerService;
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
  private UserService userService;

  @Override
  public TrainerDto getById(UUID id) {
    Trainer trainer = trainerDao.get(id).orElseThrow(
        () -> EntityNotFoundException.byId(id, Trainer.class.getSimpleName())
    );
    User user = userService.getExistingById(trainer.getUserId());
    return trainerDtoFromUserAndTrainer(user, trainer);
  }

  @Override
  public Trainer create(Trainer trainer) {
    if (userService.getById(trainer.getUserId()).isEmpty()){
      log.error("Impossible to create Trainee, user with id: {} does not exist", trainer.getUserId());
      throw EntityNotFoundException.byId(trainer.getUserId(), User.class.getSimpleName());
    }
    return trainerDao.save(trainer);
  }

  @Override
  public TrainerDto create(TrainerDto trainerDto) {
    User user = userService.getById(trainerDto.getUserId())
        .orElseGet(() -> userService.create(trainerDto));

    trainerDto.setUserId(user.getId());
    Trainer trainee = trainerMapper.newTrainerFromDto(trainerDto);
    return trainerDtoFromUserAndTrainer(user, trainee);
  }

  @Override
  public Trainer update(Trainer trainer) {
    Optional<Trainer> storedTrainee = trainerDao.get(trainer.getId());
    if (storedTrainee.isEmpty()){
      throw EntityNotFoundException.byId(trainer.getId(), Trainer.class.getSimpleName());
    }
    log.info("Updating saved trainee from dto");
    trainerMapper.updateEntityFromEntity(trainer, storedTrainee.get());
    return trainerDao.save(storedTrainee.get());
  }

  @Override
  public TrainerDto update(TrainerDto trainerDto) {
    User storedUser = userService.update(trainerDto);
    Trainer trainee = update(trainerMapper.newTrainerFromDto(trainerDto));
    return trainerDtoFromUserAndTrainer(storedUser, trainee);
  }

  private TrainerDto trainerDtoFromUserAndTrainer(User user, Trainer trainee) {
    TrainerDto traineeDto = trainerMapper.fromUser(user);
    trainerMapper.updateDtoFromEntity(trainee, traineeDto);
    return traineeDto;
  }
}
