package com.example.steps;

import com.example.dto.TrainingDto;
import io.cucumber.datatable.DataTable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


public class BodyBuilder {

  public static Object objectOf(String type, DataTable dataTable) {
    switch (type) {
      case "TrainingDto":
        return buildTrainingDto(dataTable);
      case "DurationMap":
        return buildDurationMap(dataTable);
    }
    throw new IllegalArgumentException("Unsupported type: " + type);
  }

  private static Map<Integer, Map<Integer, Long>> buildDurationMap(DataTable dataTable) {
    Map<Integer, Map<Integer, Long>> rez = new HashMap<>();
    try {
      for (int i = 1; i < dataTable.height(); i++) {
        var year = Integer.parseInt(dataTable.cell(i, 0));
        var month = Integer.parseInt(dataTable.cell(i, 1));
        var val = Long.parseLong(dataTable.cell(i, 2));

        var yearMap = rez.get(year);
        if (yearMap == null) {
          yearMap = new HashMap<>();
        }
        var monthValue = yearMap.get(month);
        if (monthValue == null) {
          monthValue = 0L;
        }
        yearMap.put(month, monthValue + val);
        rez.put(year, yearMap);
      }
    } catch (Exception ignored) {}
    return rez;
  }

  private static TrainingDto buildTrainingDto(DataTable dataTable) {
    return TrainingDto.builder()
        .traineeUsername(dataTable.cell(1, 1))
        .trainerUsername(dataTable.cell(2, 1))
        .name(dataTable.cell(3, 1))
        .trainingType(dataTable.cell(4, 1))
        .date(dataTable.cell(5, 1))
        .duration(Long.parseLong(dataTable.cell(6, 1)))
        .build();
  }


//  public static UserCredentialsDto buildUserCredentialsDto(DataTable dataTable) {
//    return UserCredentialsDto.builder()
//        .username(dataTable.cell(1, 1))
//        .password(dataTable.cell(2, 1))
//        .build();
//  }
//
//  public static TrainerDto buildTrainerDto(DataTable dataTable) {
//    return TrainerDto.builder()
//        .firstName(dataTable.cell(1, 1))
//        .lastName(dataTable.cell(2, 1))
//        .username(dataTable.cell(3, 1))
//        .specialization(dataTable.cell(4, 1))
//        .build();
//  }
//
//  public static TraineeDto buildTraineeDto(DataTable dataTable) {
//    return TraineeDto.builder()
//        .firstName(dataTable.cell(1, 1))
//        .lastName(dataTable.cell(2, 1))
//        .username(dataTable.cell(3, 1))
//        .address(dataTable.cell(4, 1))
//        .birthDate(LocalDate.parse(dataTable.cell(5, 1)))
//        .build();
//  }
//
//  public static UpdateTrainersListDto buildUpdateTrainersListDto(DataTable dataTable) {
//    List<String> trainers = new ArrayList<>();
//    try {
//      for (int i = 1; i < 1000; i++) {
//        trainers.add(dataTable.cell(i, 0));
//      }
//    } catch (Exception ignored) {
//    }
//    return UpdateTrainersListDto.builder()
//        .trainers(trainers)
//        .build();
//  }
}
