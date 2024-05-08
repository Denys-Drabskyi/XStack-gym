package com.example.trainersservice.dao;

import com.example.trainersservice.entity.TrainingSummary;
import com.example.trainersservice.repository.mongo.TrainingSummaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Profile("mongo")
@RequiredArgsConstructor
public class MongoTrainingSummaryDao implements TrainingSummaryDao {
    private final TrainingSummaryRepository repository;

    @Override
    public TrainingSummary save(TrainingSummary trainingSummary) {
        return repository.insert(trainingSummary);
    }

    @Override
    public TrainingSummary update(TrainingSummary trainingSummary) {
        return repository.save(trainingSummary);
    }

    @Override
    public Optional<TrainingSummary> getByTrainerUsername(String username) {
        return repository.findByUsername(username);
    }
}
