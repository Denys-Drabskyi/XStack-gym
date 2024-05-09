package com.example.trainersservice.entity;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class TrainingYear {
  private int year;
  private List<TrainingMonth> months;

  public static TrainingYear of(int year) {
    TrainingYear trainingYear = new TrainingYear();
    trainingYear.year = year;
    trainingYear.months = new ArrayList<>();
    return trainingYear;
  }
}
