package org.example.controller;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.dto.TraineeDto;
import org.example.dto.TraineeDtoWithTrainers;
import org.example.dto.TrainerDto;
import org.example.dto.TrainingDto;
import org.example.dto.UpdateTrainersListDto;
import org.example.dto.UserCredentialsDto;
import org.example.dto.Views;
import org.example.service.AuthService;
import org.example.service.TraineeService;
import org.example.service.TrainerService;
import org.example.service.TrainingService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
  private final AuthService authService;
  private final TrainingService trainingService;
  private final TrainerService trainerService;

  @JsonView(Views.Credentials.class)
  @PostMapping("/register")
  public ResponseEntity<UserCredentialsDto> registerTrainee(@Valid @RequestBody TraineeDto dto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(authService.createTrainee(dto));
  }

  @JsonView(Views.PublicTrainee.class)
  @GetMapping("/{username}")
  public ResponseEntity<TraineeDtoWithTrainers> getTrainee(
      @PathVariable("username") String username
  ) {
    return ResponseEntity.ok(service.getWithTrainers(username));
  }

  @JsonView(Views.PublicTrainee.class)
  @PutMapping
  public ResponseEntity<TraineeDtoWithTrainers> updateTrainee(
      @AuthenticationPrincipal UserDetails userDetails,
      @Valid @RequestBody TraineeDto dto
  ) {
    if (!userDetails.getUsername().equals(dto.getUsername())) {
      throw new AccessDeniedException("You are not allowed to update this trainee");
    }
    return ResponseEntity.ok(service.update(dto));
  }

  @DeleteMapping
  public ResponseEntity<Void> deleteTrainee(
      @AuthenticationPrincipal UserDetails userDetails
  ) {
    service.deleteByUsername(userDetails.getUsername());
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @JsonView(Views.InList.class)
  @PutMapping("/trainer")
  public ResponseEntity<List<TrainerDto>> updateTraineesTrainers(
      @AuthenticationPrincipal UserDetails userDetails,
      @RequestBody UpdateTrainersListDto dto
  ) {
    return ResponseEntity.ok(trainerService.updateTrainers(userDetails.getUsername(), dto));
  }

  @JsonView(Views.InList.class)
  @GetMapping("/trainer")
  public ResponseEntity<List<TrainerDto>> getNotAssignedActiveTrainers(
      @AuthenticationPrincipal UserDetails userDetails
  ) {
    return ResponseEntity.ok(trainerService.getTrainersNotAssignedToTrainee(userDetails.getUsername()));
  }

  //  @JsonView(Views.InList.class)
  @GetMapping("/trainings")
  public ResponseEntity<List<TrainingDto>> getTraineeTrainings(
      @AuthenticationPrincipal UserDetails userDetails,
      @RequestParam("trainers") List<String> trainers,
      @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date from,
      @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date to
  ) {
    return ResponseEntity.ok(
        trainingService.getTraineeTrainingListByTrainerAndDateBetween(userDetails.getUsername(), trainers, from, to));
  }
}
