package org.example.controller;

import com.fasterxml.jackson.annotation.JsonView;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.aop.Auth;
import org.example.dto.OnAuth;
import org.example.dto.TrainerDto;
import org.example.dto.TrainerDtoWithTrainees;
import org.example.dto.TrainingDto;
import org.example.dto.UserCredentialsDto;
import org.example.dto.Views;
import org.example.service.TrainerService;
import org.example.service.TrainingService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trainer")
@RequiredArgsConstructor
public class TrainerController {
  private final TrainerService service;
  private final TrainingService trainingService;

  @JsonView(Views.Credentials.class)
  @PostMapping("/register")
  public ResponseEntity<UserCredentialsDto> registerTrainer(@Valid @RequestBody TrainerDto dto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
  }

  @Auth
  @JsonView(Views.PublicTrainer.class)
  @GetMapping("/{username}")
  public ResponseEntity<TrainerDtoWithTrainees> getTrainer(
      @Validated(OnAuth.class) @RequestBody UserCredentialsDto credentials,
      @PathVariable("username") String username
  ) {
    return ResponseEntity.ok(service.getByUsername(username));
  }

  @Auth
  @JsonView(Views.PublicTrainer.class)
  @PutMapping
  public ResponseEntity<TrainerDtoWithTrainees> updateTrainer(
      @Validated(OnAuth.class) @RequestBody TrainerDto dto
  ) {
    return ResponseEntity.ok(service.update(dto));
  }

  @Auth
  @JsonView(Views.InList.class)
  @GetMapping("/unassigned")
  public ResponseEntity<List<TrainerDto>> getUnassignedTrainers(
      @Validated(OnAuth.class) @RequestBody UserCredentialsDto dto
  ) {
    return ResponseEntity.ok(service.getTrainersNotAssignedToTrainee(dto));
  }

  @Auth
  @GetMapping("/trainings")
  public ResponseEntity<List<TrainingDto>> getTrainerTrainings(
      @Validated(OnAuth.class) @RequestBody UserCredentialsDto dto,
      @RequestParam("trainers") List<String> trainers,
      @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date from,
      @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date to
  ) {
    return ResponseEntity.ok(trainingService.getTrainerTrainingListByTraineeAndDateBetween(dto.getUsername(), trainers, from, to));
  }
}
