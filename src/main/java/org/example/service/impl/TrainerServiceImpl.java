package org.example.service.impl;

import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.example.dao.TrainerDao;
import org.example.entity.Trainer;
import org.example.exception.EntityCreatingException;
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
  public Optional<Trainer> getById(UUID id) {
    return trainerDao.get(id);
  }

  @Override
  public Trainer getExistingById(UUID id) {
    return getById(id)
        .orElseThrow(() -> EntityNotFoundException.byId(id, Trainer.class.getSimpleName()));
  }

  @Override
  public Trainer create(Trainer trainer) {
    if (userService.existsById(trainer.getId())){
      throw EntityCreatingException.alreadyExists(trainer.getId(), Trainer.class.getSimpleName());
    }
    userService.checkForUsernameAvailable(trainer);
    return trainerDao.save(trainer);
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
}
