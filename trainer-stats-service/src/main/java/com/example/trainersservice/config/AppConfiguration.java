package com.example.trainersservice.config;

import com.example.trainersservice.entity.TrainerMonth;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
  @Bean
  public List<TrainerMonth> storage() {
    return new CopyOnWriteArrayList<>();
  }
}
