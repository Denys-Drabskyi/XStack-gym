package com.example.trainersservice.entity;

import lombok.Data;

@Data
public class TrainingMonth {
    private int month;
    private long summaryDuration;

    public static TrainingMonth of(int month) {
        TrainingMonth m = new TrainingMonth();
        m.month = month;
        return m;
    }
}
