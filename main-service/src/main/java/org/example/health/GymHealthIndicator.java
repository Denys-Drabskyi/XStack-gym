package org.example.health;

import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dao.UserDao;
import org.example.service.TrainingTypeService;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class GymHealthIndicator implements HealthIndicator {
  private final TrainingTypeService trainingTypeService;
  private final UserDao userDao;

  @Override
  public Health health() {
    boolean up = true;
    Map<String, Object> details = new HashMap<>();

    int trainingTypesSize = trainingTypeService.getTypes().size();
    log.info("trainingTypesSize = {}", trainingTypesSize);
    if (trainingTypesSize == 0) {
      log.error("No training types found");
      up = false;
    }
    details.put("TrainingTypeCount", trainingTypesSize);
    details.put("ActiveUsersCount", userDao.activeUsers());

    return up ? Health.up().withDetails(details).build()
        : Health.down().withDetails(details).build();
  }
}
