package com.example.trainersservice.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@Document("training-summary")
public class TrainingSummary {
    @Id
    private UUID id;
    private String username;
    private String firstname;
    private String lastname;
    private boolean isActive;
    private List<TrainingYear> years;
}
