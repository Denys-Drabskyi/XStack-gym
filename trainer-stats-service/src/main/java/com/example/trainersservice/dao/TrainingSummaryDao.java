package com.example.trainersservice.dao;

import com.example.trainersservice.entity.TrainingSummary;

import java.util.Optional;

public interface TrainingSummaryDao {
    TrainingSummary save(TrainingSummary trainingSummary);
    TrainingSummary update(TrainingSummary trainingSummary);
    Optional<TrainingSummary> getByTrainerUsername(String username);
}
