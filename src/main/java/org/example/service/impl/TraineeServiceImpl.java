package org.example.service.impl;

import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.example.dao.TraineeDao;
import org.example.dao.TrainingDao;
import org.example.entity.Trainee;
import org.example.exception.EntityCreatingException;
import org.example.exception.EntityNotFoundException;
import org.example.mapper.TraineeMapper;
import org.example.service.TraineeService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TraineeServiceImpl implements TraineeService {
  @Autowired
  private TraineeDao traineeDao;
  @Autowired
  private TrainingDao trainingDao;
  @Autowired
  private UserService userService;
  @Autowired
  private TraineeMapper traineeMapper;

  @Override
  public Optional<Trainee> getById(UUID id) {
    return traineeDao.get(id);
  }

  @Override
  public Trainee getExistingById(UUID id) {
    return getById(id)
        .orElseThrow(() -> EntityNotFoundException.byId(id, Trainee.class.getSimpleName()));
  }

  @Override
  public Trainee create(Trainee trainee) {
    if (userService.existsById(trainee.getId())){
      throw EntityCreatingException.alreadyExists(trainee.getId(), Trainee.class.getSimpleName());
    }
    userService.checkForUsernameAvailable(trainee);
    return traineeDao.save(trainee);
  }

  @Override
  public Trainee update(Trainee trainee) {
    Optional<Trainee> storedTrainee = traineeDao.get(trainee.getId());
    if (storedTrainee.isEmpty()){
      throw EntityNotFoundException.byId(trainee.getId(), Trainee.class.getSimpleName());
    }
    log.info("Updating saved trainee from dto");
    traineeMapper.updateEntityFromEntity(trainee, storedTrainee.get());
    return traineeDao.save(storedTrainee.get());
  }

  @Override
  public void deleteById(UUID id) {
    Optional<Trainee> trainee = traineeDao.get(id);
    if (trainee.isPresent()) {
      trainingDao.deleteUserTrainings(id);
      traineeDao.delete(id);
    }
  }
}
