package com.example.trainersservice.jms;

import com.example.trainersservice.service.TrainingSummaryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.jms.Message;
import jakarta.jms.TextMessage;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.RegisterTrainingEventDto;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("jms")
@RequiredArgsConstructor
public class JmsConsumerService {
  private final JmsProducerService jmsProducerService;
  private final ObjectMapper objectMapper;
  private final TrainingSummaryService trainingSummaryService;

  @Value("${spring.activemq.queue.dead-letter}")
  private String deadLetterQueue;

  @JmsListener(destination = "${spring.activemq.queue.trainer-training-queue}")
  public void registerTrainingEvent(final Message jsonMessage) {
    MDC.put("transactionId", String.valueOf(UUID.randomUUID()));

    log.info("Received message from mainService");
    log.debug("message: {}", jsonMessage);
    try {
      if (jsonMessage instanceof TextMessage textMessage) {
        String messageData = textMessage.getText();
        RegisterTrainingEventDto dto = objectMapper.readValue(messageData, RegisterTrainingEventDto.class);
        trainingSummaryService.processTrainingEvent(dto);
      }
    } catch (Exception e) {
      log.info("Error processing training event");
      log.debug("Exception:", e);
      jmsProducerService.sendToQueue(deadLetterQueue, e);
    }

    MDC.clear();
  }
}
