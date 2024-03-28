package org.example.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.aop.Auth;
import org.example.dto.OnAuth;
import org.example.dto.TrainingDto;
import org.example.dto.UserCredentialsDto;
import org.example.entity.TrainingType;
import org.example.service.TrainingService;
import org.example.service.TrainingTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/training")
@RequiredArgsConstructor
public class TrainingController {
  private final TrainingService trainingService;
  private final TrainingTypeService trainingTypeService;

  @PostMapping
  public ResponseEntity<Void> addTraining(
      @Valid @RequestBody TrainingDto trainingDto
  ) {
    trainingService.create(trainingDto);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @Auth
  @GetMapping("/type")
  public ResponseEntity<List<TrainingType>> getTypes(
      @Validated(OnAuth.class) @RequestBody UserCredentialsDto credentials
  ) {
    return ResponseEntity.ok(trainingTypeService.getTypes());
  }

}
