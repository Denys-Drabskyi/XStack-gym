package com.example.trainersservice.repository.mongo;

import com.example.trainersservice.entity.TrainingSummary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

@Profile("mongo")
public interface TrainingSummaryRepository extends MongoRepository<TrainingSummary, UUID> {
    Optional<TrainingSummary> findByUsername(String username);
}
