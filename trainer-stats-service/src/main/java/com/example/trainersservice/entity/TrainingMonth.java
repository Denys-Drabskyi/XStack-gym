package com.example.trainersservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainingMonth {
    private int month;
    private long summaryDuration;

    public static TrainingMonth of(int month) {
        TrainingMonth m = new TrainingMonth();
        m.month = month;
        return m;
    }
}
