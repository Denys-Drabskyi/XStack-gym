package org.example.integration;

import io.cucumber.datatable.DataTable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.example.dto.TraineeDto;
import org.example.dto.TrainerDto;
import org.example.dto.UpdateTrainersListDto;
import org.example.dto.UserCredentialsDto;

public class BodyBuilderHelper {

  public static Object objectOf(String type, DataTable dataTable) {
    switch (type) {
      case "TraineeDto":
        return buildTraineeDto(dataTable);
      case "UpdateTrainersListDto":
        return buildUpdateTrainersListDto(dataTable);
      case "TrainerDto":
        return buildTrainerDto(dataTable);
      case "UserCredentialsDto":
        return buildUserCredentialsDto(dataTable);
    }
    throw new IllegalArgumentException("Unsupported type: " + type);
  }


  public static UserCredentialsDto buildUserCredentialsDto(DataTable dataTable) {
    return UserCredentialsDto.builder()
        .username(dataTable.cell(1, 1))
        .password(dataTable.cell(2, 1))
        .build();
  }

  public static TrainerDto buildTrainerDto(DataTable dataTable) {
    return TrainerDto.builder()
        .firstName(dataTable.cell(1, 1))
        .lastName(dataTable.cell(2, 1))
        .username(dataTable.cell(3, 1))
        .specialization(dataTable.cell(4, 1))
        .build();
  }

  public static TraineeDto buildTraineeDto(DataTable dataTable) {
    return TraineeDto.builder()
        .firstName(dataTable.cell(1, 1))
        .lastName(dataTable.cell(2, 1))
        .username(dataTable.cell(3, 1))
        .address(dataTable.cell(4, 1))
        .birthDate(LocalDate.parse(dataTable.cell(5, 1)))
        .build();
  }

  public static UpdateTrainersListDto buildUpdateTrainersListDto(DataTable dataTable) {
    List<String> trainers = new ArrayList<>();
    try {
      for (int i = 1; i < 1000; i++) {
        trainers.add(dataTable.cell(i, 0));
      }
    } catch (Exception ignored) {
    }
    return UpdateTrainersListDto.builder()
        .trainers(trainers)
        .build();
  }
}
