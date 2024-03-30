package org.example.health;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.example.dao.UserDao;
import org.example.service.TrainingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class PrometheusMetrics {

  @Bean
  public Gauge userCount(MeterRegistry registry, UserDao dao) {
    return Gauge.builder("usersCount", dao::activeUsers)
        .description("Total number of active users")
        .register(registry);
  }

  @Bean
  public Gauge averageTrainerTrainings(MeterRegistry registry, TrainingService service) {
    return Gauge.builder("averageTrainerTrainings", service::getAverageTrainerTrainingsCount)
        .description("average Trainer Trainings count")
        .register(registry);
  }


}
