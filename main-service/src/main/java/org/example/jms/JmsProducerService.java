package org.example.jms;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.jms.TextMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("jms")
@RequiredArgsConstructor
public class JmsProducerService {
  private final JmsTemplate jmsTemplate;

  public void sendToQueue(String queue, Object objectToSend) {
    try {
      log.info("Sending message to queue: {}", queue);
      String jsonObj = new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(objectToSend);
      jmsTemplate.send(queue, messageCreator -> {
        TextMessage message = messageCreator.createTextMessage();
        message.setText(jsonObj);
        return message;
      });
    } catch (Exception ex) {
      log.info("Error sending message to queue", ex);
      System.out.println("ERROR in sending message to queue");
    }
  }
}