package com.example.trainersservice.controller;

import com.example.trainersservice.service.TrainingSummaryService;
import lombok.RequiredArgsConstructor;
import org.example.dto.RegisterTrainingEventDto;
import org.example.dto.TrainerStatisticsDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trainer")
@RequiredArgsConstructor
public class TrainerController {
  private final TrainingSummaryService trainingSummaryService;

  @PostMapping
  private ResponseEntity<Void> processTrainingEvent(@RequestBody RegisterTrainingEventDto dto) {
    trainingSummaryService.processTrainingEvent(dto);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @GetMapping("/{username}")
  private ResponseEntity<TrainerStatisticsDto> getTrainerStatistic(@PathVariable String username) {
    return ResponseEntity.ok(trainingSummaryService.getTrainerStatistic(username));
  }
}
