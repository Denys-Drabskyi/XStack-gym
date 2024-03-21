package org.example.configuration.health;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.example.dao.UserDao;
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
}
