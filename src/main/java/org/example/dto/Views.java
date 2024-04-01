package org.example.dto;

public interface Views {
  interface InList {}
  interface PublicTrainee extends InList {}
  interface PublicTrainer extends InList {}
  interface Private extends PublicTrainee, PublicTrainer {}

  interface Credentials {}

//  interface Trainer {
//    interface PublicTrainer extends All.InList {}
//    interface UpdateTrainer extends PublicTrainer {}
//  }
//
//  interface Trainee {
//    interface PublicTrainee extends All.InList {}
//    interface UpdateTrainee extends PublicTrainee {}
//  }
//
//  interface All {
//    interface InList {}
//    interface Private extends PublicTrainee, UpdateTrainer {}
//  }
}
