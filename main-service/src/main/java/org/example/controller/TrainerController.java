package org.example.controller;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.dto.TrainerDto;
import org.example.dto.TrainerDtoWithTrainees;
import org.example.dto.TrainingDto;
import org.example.dto.UserCredentialsDto;
import org.example.dto.Views;
import org.example.service.AuthService;
import org.example.service.TrainerService;
import org.example.service.TrainingService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
  private final AuthService authService;

  @JsonView(Views.Credentials.class)
  @PostMapping("/register")
  public ResponseEntity<UserCredentialsDto> registerTrainer(@Valid @RequestBody TrainerDto dto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(authService.createTrainer(dto));
  }

  @JsonView(Views.PublicTrainer.class)
  @GetMapping("/{username}")
  public ResponseEntity<TrainerDtoWithTrainees> getTrainer(
      @PathVariable("username") String username
  ) {
    return ResponseEntity.ok(service.getByUsername(username));
  }

  @JsonView(Views.PublicTrainer.class)
  @PutMapping
  public ResponseEntity<TrainerDtoWithTrainees> updateTrainer(
      @AuthenticationPrincipal UserDetails userDetails,
      @Valid @RequestBody TrainerDto dto
  ) {
    if (!userDetails.getUsername().equals(dto.getUsername())) {
      throw new AccessDeniedException("You are not allowed to update this trainer");
    }
    return ResponseEntity.ok(service.update(dto));
  }

  @GetMapping("/trainings")
  public ResponseEntity<List<TrainingDto>> getTrainerTrainings(
      @AuthenticationPrincipal UserDetails userDetails,
      @RequestParam("trainees") List<String> trainees,
      @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date from,
      @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date to
  ) {
    return ResponseEntity.ok(
        trainingService.getTrainerTrainingListByTraineeAndDateBetween(userDetails.getUsername(), trainees, from, to));
  }
}
