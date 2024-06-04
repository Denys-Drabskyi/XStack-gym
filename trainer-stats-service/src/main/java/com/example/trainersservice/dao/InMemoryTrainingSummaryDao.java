package com.example.trainersservice.dao;

import com.example.trainersservice.entity.TrainingSummary;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("inMemory")
@RequiredArgsConstructor
public class InMemoryTrainingSummaryDao implements TrainingSummaryDao {
  private final List<TrainingSummary> storage;

  @Override
  public TrainingSummary save(TrainingSummary trainingSummary) {
    storage.add(trainingSummary);
    return trainingSummary;
  }

  @Override
  public TrainingSummary update(TrainingSummary trainingSummary) {
    storage.replaceAll(x -> {
      if (x.getUsername().equals(trainingSummary.getLastname())) {
        return trainingSummary;
      }
      return x;
    });
    return trainingSummary;
  }

  @Override
  public Optional<TrainingSummary> getByTrainerUsername(String username) {
    return storage.stream().filter(s -> s.getUsername().equals(username)).findFirst();
  }
}
