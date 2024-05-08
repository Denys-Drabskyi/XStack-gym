package com.example.trainersservice.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Deprecated //for old in memory impl //todo replace with TrainingSummary
public class TrainerMonth {
  private String username;
  private String firstname;
  private String lastname;
  private int year;
  private int month;
  private boolean isActive;
  private long totalTrainingDuration;

  public boolean isAt(int year, int month) {
    return this.year == year && this.month == month;
  }

  public void subtractTrainingDuration(long trainingDuration) {
    if (totalTrainingDuration < trainingDuration) {
      throw new IllegalArgumentException("Training duration cannot be less than the total training duration");
    }
    totalTrainingDuration -= trainingDuration;
  }

  public void addTrainingDuration(long trainingDuration) {
    totalTrainingDuration += trainingDuration;
  }
}
