package com.example.trainersservice.entity;

import lombok.Data;

import java.util.List;

@Data
public class TrainingYear {
    private int year;
    private List<TrainingMonth> months;

    public static TrainingYear of(int year) {
        TrainingYear trainingYear = new TrainingYear();
        trainingYear.year = year;
        return trainingYear;
    }
}
