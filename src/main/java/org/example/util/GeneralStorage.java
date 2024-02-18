package org.example.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.Getter;
import org.example.configuration.properties.DaoFile;
import org.example.entity.Trainee;
import org.example.entity.Trainer;
import org.example.entity.Training;
import org.example.entity.User;
import org.example.exception.StorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Component
public class GeneralStorage {
  private DaoFile src;
  private ObjectMapper objectMapper;
  private Map<UUID, Trainee> traineeStorage;
  private Map<UUID, Trainer> trainerStorage;
  private Map<UUID, Training> trainingStorage;
  private Map<UUID, User> userStorage = new HashMap<>();

  @Autowired
  public void setSrc(DaoFile src) {
    this.src = src;
  }

  @Autowired
  public void setObjectMapper(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @PostConstruct
  void postConstruct() {
    try {
      traineeStorage = objectMapper.readValue(new File(src.getTrainee()), new TypeReference<>() {});
      trainerStorage = objectMapper.readValue(new File(src.getTrainer()), new TypeReference<>() {});
      trainingStorage = objectMapper.readValue(new File(src.getTraining()), new TypeReference<>() {});
      userStorage = objectMapper.readValue(new File(src.getUser()), new TypeReference<>() {});
    } catch (Exception e) {
      throw StorageException.initialization(e);
    }
  }

  @PreDestroy
  void preDestroy() {
    try {
      objectMapper.writeValue(new File(src.getTrainee()), traineeStorage);
      objectMapper.writeValue(new File(src.getTrainer()), trainerStorage);
      objectMapper.writeValue(new File(src.getTraining()), trainingStorage);
      objectMapper.writeValue(new File(src.getUser()), userStorage);
    } catch (IOException e) {
      throw StorageException.writing(e);
    }
  }
}
