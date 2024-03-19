package org.example.service.impl;

//import jakarta.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.example.dao.TraineeDao;
import org.example.dto.TraineeDto;
import org.example.dto.TraineeDtoWithTrainers;
import org.example.dto.UserCredentialsDto;
import org.example.entity.Trainee;
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
  private UserService userService;
  @Autowired
  private TraineeMapper traineeMapper;

  @Override
  public boolean existsById(UUID id) {
    return traineeDao.existById(id);
  }

  @Override
  public TraineeDto get(UserCredentialsDto credentials) {
    return traineeMapper.toDto(traineeDao.getByUsername(credentials.getUsername())
        .orElseThrow(() -> EntityNotFoundException.byUsername(credentials.getUsername(), Trainee.class.getSimpleName()))
    );
  }

  @Override
  public TraineeDtoWithTrainers getByUsername(String username) {
    return traineeMapper.toDtoWithTrainers(traineeDao.getByUsername(username)
        .orElseThrow(() -> EntityNotFoundException.byUsername(username, Trainee.class.getSimpleName())));
  }

  @Override
  @Transactional
  public TraineeDto create(TraineeDto dto) {
    return traineeMapper.toDto(traineeDao.save(createTrainee(dto)));
  }


  @Override
  @Transactional
  public TraineeDtoWithTrainers update(TraineeDto dto) {
    Optional<Trainee> storedTrainee = traineeDao.getByUsername(dto.getUsername());
    if (storedTrainee.isEmpty()){
      throw EntityNotFoundException.byUsername(dto.getUsername(), Trainee.class.getSimpleName());
    }
    updateTrainee(dto, storedTrainee.get());
    return traineeMapper.toDtoWithTrainers(traineeDao.save(storedTrainee.get()));
  }


  @Override
  public void deleteByUsername(UserCredentialsDto credentials) {
    Optional<Trainee> trainee = traineeDao.getByUsername(credentials.getUsername());
    trainee.ifPresent(value -> traineeDao.delete(value.getId()));
  }

  private void updateTrainee(TraineeDto dto, Trainee entity) {
    log.info("Updating saved trainee from dto");
    traineeMapper.updateEntityFromDto(dto, entity);
    userService.update(dto, entity.getUser());
  }

  private Trainee createTrainee(TraineeDto dto) {
    return traineeMapper.toBuilder(dto)
        .user(userService.createUser(dto))
        .build();
  }
}
