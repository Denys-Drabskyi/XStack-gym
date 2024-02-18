package org.example.service.impl;

import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.example.dao.TraineeDao;
import org.example.dao.TrainingDao;
import org.example.dto.TraineeDto;
import org.example.entity.Trainee;
import org.example.entity.User;
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
  public TraineeDto getById(UUID id) {
    Trainee trainee = traineeDao.get(id).orElseThrow(
        () -> EntityNotFoundException.byId(id, Trainee.class.getSimpleName())
    );
    User user = userService.getExistingById(trainee.getUserId());
    return traineeDtoFromUserAndTrainee(user, trainee);
  }

  @Override
  public Trainee create(Trainee trainee) {
    if (userService.getById(trainee.getUserId()).isEmpty()){
      log.error("Impossible to create Trainee, user with id: {} does not exist", trainee.getUserId());
      throw EntityNotFoundException.byId(trainee.getUserId(), User.class.getSimpleName());
    }
    return traineeDao.save(trainee);
  }

  @Override
  public TraineeDto create(TraineeDto traineeDto) {
    User user = userService.getById(traineeDto.getUserId())
        .orElseGet(() -> userService.create(traineeDto));

    traineeDto.setUserId(user.getId());
    Trainee trainee = traineeMapper.newTraineeFromDto(traineeDto);
    return traineeDtoFromUserAndTrainee(user, trainee);
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
  public TraineeDto update(TraineeDto traineeDto) {
    User storedUser = userService.update(traineeDto);
    Trainee trainee = update(traineeMapper.newTraineeFromDto(traineeDto));
    return traineeDtoFromUserAndTrainee(storedUser, trainee);
  }

  @Override
  public void deleteById(UUID id) {
    Optional<Trainee> trainee = traineeDao.get(id);
    if (trainee.isPresent()) {
      trainingDao.deleteUserTrainings(id);
      traineeDao.delete(id);
      userService.deleteById(trainee.get().getUserId());
    }
  }

  private TraineeDto traineeDtoFromUserAndTrainee(User user, Trainee trainee) {
    TraineeDto traineeDto = traineeMapper.fromUser(user);
    traineeMapper.updateDtoFromEntity(trainee, traineeDto);
    return traineeDto;
  }
}
