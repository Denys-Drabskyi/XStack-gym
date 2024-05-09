package com.example.trainersservice.dao;

import com.example.trainersservice.entity.TrainingSummary;
import com.example.trainersservice.repository.mongo.TrainingSummaryRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("mongo")
@RequiredArgsConstructor
public class MongoTrainingSummaryDao implements TrainingSummaryDao {
  private final TrainingSummaryRepository repository;

  @Override
  public TrainingSummary save(TrainingSummary trainingSummary) {
    log.debug("saving training summary:{}", trainingSummary);
    return repository.insert(trainingSummary);
  }

  @Override
  public TrainingSummary update(TrainingSummary trainingSummary) {
    log.debug("updating training summary:{}", trainingSummary);
    return repository.save(trainingSummary);
  }

  @Override
  public Optional<TrainingSummary> getByTrainerUsername(String username) {
    log.debug("getting training summary by username:{}", username);
    return repository.findByUsername(username);
  }
}
