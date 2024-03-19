package org.example.controller;

import com.fasterxml.jackson.annotation.JsonView;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.aop.Auth;
import org.example.dto.OnAuth;
import org.example.dto.TraineeDto;
import org.example.dto.TraineeDtoWithTrainers;
import org.example.dto.TrainerDto;
import org.example.dto.TrainingDto;
import org.example.dto.UpdateTrainersListDto;
import org.example.dto.UserCredentialsDto;
import org.example.dto.Views;
import org.example.service.TraineeService;
import org.example.service.TrainerService;
import org.example.service.TrainingService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trainee")
@RequiredArgsConstructor
public class TraineeController {
  private final TraineeService service;
  private final TrainingService trainingService;
  private final TrainerService trainerService;

  @JsonView(Views.Credentials.class)
  @PostMapping("/register")
  public ResponseEntity<UserCredentialsDto> registerTrainee(@Valid @RequestBody TraineeDto dto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
  }

  @Auth
  @JsonView(Views.PublicTrainee.class)
  @GetMapping("/{username}")
  public ResponseEntity<TraineeDtoWithTrainers> getTrainee(
      @Validated(OnAuth.class) @RequestBody UserCredentialsDto credentials,
      @PathVariable("username") String username
  ) {
    return ResponseEntity.ok(service.getByUsername(username));
  }

  @Auth
  @JsonView(Views.PublicTrainee.class)
  @PutMapping
  public ResponseEntity<TraineeDtoWithTrainers> updateTrainee(
      @Validated(OnAuth.class) @RequestBody TraineeDto dto
  ) {
    return ResponseEntity.ok(service.update(dto));
  }

  @Auth
  @DeleteMapping
  public ResponseEntity<Void> deleteTrainee(
      @Validated(OnAuth.class) @RequestBody UserCredentialsDto dto
  ) {
    service.deleteByUsername(dto);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @Auth
  @JsonView(Views.InList.class)
  @PutMapping("/trainer")
  public ResponseEntity<List<TrainerDto>> updateTraineesTrainers(
      @Validated(OnAuth.class) @RequestBody UpdateTrainersListDto dto
  ) {
    return ResponseEntity.ok(trainerService.updateTrainers(dto));
  }

  @Auth
  @JsonView(Views.InList.class)
  @GetMapping("/trainer")
  public ResponseEntity<List<TrainerDto>> getNotAssignedActiveTrainers(
      @Validated(OnAuth.class) @RequestBody UserCredentialsDto dto
  ) {
    return ResponseEntity.ok(trainerService.getTrainersNotAssignedToTrainee(dto));
  }

  @Auth
  @JsonView(Views.InList.class)
  @GetMapping("/trainings")
  public ResponseEntity<List<TrainingDto>> getTraineeTrainings(
      @Validated(OnAuth.class) @RequestBody UserCredentialsDto dto,
      @RequestParam("trainers") List<String> trainers,
      @RequestParam("from")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date from,
      @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date to
      ) {
    return ResponseEntity.ok(trainingService.getTraineeTrainingListByTrainerAndDateBetween(dto.getUsername(), trainers, from, to));
  }
}
