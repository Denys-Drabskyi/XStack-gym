package com.example.trainersservice.service;

import org.example.dto.RegisterTrainingEventDto;
import org.example.dto.TrainerStatisticsDto;


public interface TrainerService {
  void processTrainingEvent(RegisterTrainingEventDto dto);

  TrainerStatisticsDto getTrainerStatistic(String username);
}
