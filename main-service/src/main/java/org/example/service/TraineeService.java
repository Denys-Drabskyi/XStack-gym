package org.example.service;

import org.example.dto.TraineeDto;
import org.example.dto.TraineeDtoWithTrainers;

public interface TraineeService {

  TraineeDtoWithTrainers getWithTrainers(String username);

  TraineeDto create(TraineeDto dto);

  TraineeDtoWithTrainers update(TraineeDto dto);

  void deleteByUsername(String username);
}
