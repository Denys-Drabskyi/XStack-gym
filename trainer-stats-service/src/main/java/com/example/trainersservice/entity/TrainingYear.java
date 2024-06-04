package com.example.trainersservice.entity;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
