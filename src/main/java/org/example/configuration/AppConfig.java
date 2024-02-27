package org.example.configuration;


import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import java.util.UUID;
import org.example.entity.Trainee;
import org.example.entity.Trainer;
import org.example.entity.Training;
import org.example.util.GeneralStorage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "org.example")
@PropertySource("classpath:application.properties")
public class AppConfig {
  @Bean
  public ObjectMapper objectMapper(){
    return new ObjectMapper();
  }

  @Bean
  public Map<UUID, Trainee> traineeStorage(GeneralStorage storage){
    return storage.getTraineeStorage();
  }
  @Bean
  public Map<UUID, Trainer> trainerStorage(GeneralStorage storage){
    return storage.getTrainerStorage();
  }
  @Bean
  public Map<UUID, Training> trainingStorage(GeneralStorage storage){
    return storage.getTrainingStorage();
  }
}
