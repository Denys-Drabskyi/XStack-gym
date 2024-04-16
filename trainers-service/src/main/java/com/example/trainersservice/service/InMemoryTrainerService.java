package com.example.trainersservice.service;

import com.example.trainersservice.entity.TrainerMonth;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.example.dto.RegisterTrainingEventDto;
import org.example.dto.TrainerStatisticsDto;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("inMemory")
@RequiredArgsConstructor
public class InMemoryTrainerService implements TrainerService {
  private final List<TrainerMonth> storage;

  @Override
  public void processTrainingEvent(RegisterTrainingEventDto dto) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(dto.getTrainingDate());

    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH) + 1;

    switch (dto.getActionType()) {
      case ADD -> addTraining(dto, year, month);
      case DELETE -> deleteTraining(dto, year, month);
    }
  }

  @Override
  public TrainerStatisticsDto getTrainerStatistic(String username) {
    var trainerMonths = storage.stream()
        .filter(m -> m.getUsername().equals(username))
        .toList();

    if (trainerMonths.isEmpty()) {
      return null;
    }

    var statistic = trainerMonths.stream()
        .collect(Collectors.groupingBy(TrainerMonth::getYear,
            Collectors.toMap(TrainerMonth::getMonth, TrainerMonth::getTotalTrainingDuration)));
    var last = trainerMonths.stream()
        .max((m1, m2) -> m1.getYear() == m2.getYear()
            ? Integer.compare(m1.getMonth(), m2.getMonth())
            : Integer.compare(m1.getYear(), m2.getYear()))
        .orElseThrow(() -> new IllegalStateException("Last TrainerMonth does not exist, but must"));

    return toTrainerStatisticsDto(last, statistic);
  }

  private void addTraining(RegisterTrainingEventDto dto, int year, int month) {
    storage.stream()
        .filter(m -> m.isAt(year, month))
        .findFirst()
        .ifPresentOrElse(
            m -> m.addTrainingDuration(dto.getTrainingDuration()),
            () -> storage.add(toMonth(dto, year, month))
        );
  }

  private void deleteTraining(RegisterTrainingEventDto dto, int year, int month) {
    var storedMonth = storage.stream()
        .filter(m -> m.getUsername().equals(dto.getUsername()) && m.isAt(year, month))
        .findFirst()
        .orElseThrow(
            () -> new IllegalStateException("Delete training request on month that does not exist"));
    storedMonth.subtractTrainingDuration(dto.getTrainingDuration());
  }

  private TrainerMonth toMonth(RegisterTrainingEventDto dto, int year, int month) {
    return TrainerMonth.builder()
        .username(dto.getUsername())
        .firstname(dto.getFirstname())
        .lastname(dto.getLastname())
        .year(year)
        .month(month)
        .isActive(dto.isActive())
        .totalTrainingDuration(dto.getTrainingDuration())
        .build();
  }

  private TrainerStatisticsDto toTrainerStatisticsDto(TrainerMonth last, Map<Integer, Map<Integer, Long>> statistic) {
    return TrainerStatisticsDto.builder()
        .username(last.getUsername())
        .firstname(last.getFirstname())
        .lastname(last.getLastname())
        .isActive(last.isActive())
        .duration(statistic)
        .build();
  }
}
