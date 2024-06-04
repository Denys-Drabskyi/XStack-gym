package org.example.notifier.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.RegisterTrainingEventDto;
import org.example.jms.JmsProducerService;
import org.example.notifier.TrainingStatsServiceNotifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("jms")
@RequiredArgsConstructor
public class JmsTrainingServiceNotifier implements TrainingStatsServiceNotifier {
  @Value("${spring.activemq.queue.trainer-training-queue}")
  String queue;

  private final JmsProducerService producerService;

  @Override
  public void registerTrainingEvent(RegisterTrainingEventDto registerTrainingEventDto) {
    log.info("Registering training event in training stats service");
    producerService.sendToQueue(queue, registerTrainingEventDto);
    log.info("Registered training event in training stats service");
  }
}
