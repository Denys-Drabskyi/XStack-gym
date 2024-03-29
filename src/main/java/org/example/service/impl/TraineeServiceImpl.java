package org.example.service.impl;

import jakarta.transaction.Transactional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.example.dao.TraineeDao;
import org.example.dto.TraineeDto;
import org.example.dto.TraineeDtoWithTrainers;
import org.example.dto.UserCredentialsDto;
import org.example.entity.Trainee;
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
    return traineeMapper.toDto(traineeDao.getByUsername(credentials.getUsername()));
  }

  @Override
  public TraineeDtoWithTrainers getWithTrainers(String username) {
    return traineeMapper.toDtoWithTrainers(traineeDao.getByUsername(username));
  }

  @Override
  @Transactional
  public TraineeDto create(TraineeDto dto) {
    log.info("Started new trainee creation");
    Trainee rez = traineeMapper.toBuilder(dto)
        .user(userService.createUser(dto))
        .build();
    rez = traineeDao.save(rez);
    log.info("Created new trainee with username:{}", rez.getUser().getUsername());
    return traineeMapper.toDto(rez);
  }

  @Override
  @Transactional
  public TraineeDtoWithTrainers update(TraineeDto dto) {
    Trainee storedTrainee = traineeDao.getByUsername(dto.getUsername());

    log.info("Updating existing trainee with username:{} from dto", dto.getUsername());
    traineeMapper.updateEntityFromDto(dto, storedTrainee);
    userService.update(dto, storedTrainee.getUser());
    traineeDao.save(storedTrainee);
    log.info("Updated existing trainee with username:{}", dto.getUsername());

    return traineeMapper.toDtoWithTrainers(storedTrainee);
  }

  @Override
  public void deleteByUsername(String username) {
    log.info("Deleting trainee with username:{}", username);
    Trainee trainee = traineeDao.getByUsername(username);
    traineeDao.delete(trainee.getId());
    log.info("Deleted trainee with username:{}", username);
  }
}
