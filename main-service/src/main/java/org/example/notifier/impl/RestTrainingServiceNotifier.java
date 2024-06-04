package org.example.notifier.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.controller.feign.trainerStatsService.TrainerStatsServiceApi;
import org.example.dto.RegisterTrainingEventDto;
import org.example.notifier.TrainingStatsServiceNotifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("rest")
@RequiredArgsConstructor
public class RestTrainingServiceNotifier implements TrainingStatsServiceNotifier {
  private final TrainerStatsServiceApi trainerStatsService;

  @Override
  public void registerTrainingEvent(RegisterTrainingEventDto event) {
    log.info("Registering training event in training stats service");
    trainerStatsService.registerTrainingEvent(event);
    log.info("Registered training event in training stats service");
  }
}
