package org.example.controller;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.example.dto.TrainingDto;
import org.example.entity.TrainingType;
import org.example.service.TrainingService;
import org.example.service.TrainingTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  @DeleteMapping("/{trainingId}")
  public ResponseEntity<Void> deleteTraining(
      @PathVariable UUID trainingId
  ) {
    trainingService.deleteTraining(trainingId);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @GetMapping("/type")
  public ResponseEntity<List<TrainingType>> getTypes() {
    return ResponseEntity.ok(trainingTypeService.getTypes());
  }

}
