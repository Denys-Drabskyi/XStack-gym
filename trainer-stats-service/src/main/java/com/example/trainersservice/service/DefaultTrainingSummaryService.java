package com.example.trainersservice.service;

import com.example.trainersservice.dao.TrainingSummaryDao;
import com.example.trainersservice.entity.TrainingMonth;
import com.example.trainersservice.entity.TrainingSummary;
import com.example.trainersservice.entity.TrainingYear;
import com.example.trainersservice.exception.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.ActionType;
import org.example.dto.RegisterTrainingEventDto;
import org.example.dto.TrainerStatisticsDto;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DefaultTrainingSummaryService implements TrainingSummaryService {
  private final TrainingSummaryDao dao;

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
    var storedSummary = dao.getByTrainerUsername(username)
        .orElse(null);
    return storedSummary == null ? null : toTrainerStatisticsDto(storedSummary);

  }

  private void addTraining(RegisterTrainingEventDto dto, int year, int month) {
    var storedSummary = dao.getByTrainerUsername(dto.getUsername())
        .orElseGet(() -> processEventOnMissingSummary(dto));
    var trainingYear = getYear(storedSummary, year);
    var trainingMonth = getMonth(trainingYear, month);
    trainingMonth.setSummaryDuration(trainingMonth.getSummaryDuration() + dto.getTrainingDuration());
    dao.update(storedSummary);
    log.info("Added training duration:{} to {}.{} for trainer:{}", dto.getTrainingDuration(), year, month,
        dto.getUsername());
  }

  private void deleteTraining(RegisterTrainingEventDto dto, int year, int month) {
    var storedSummary = dao.getByTrainerUsername(dto.getUsername())
        .orElseGet(() -> processEventOnMissingSummary(dto));
    var trainingYear = storedSummary.getYears().stream()
        .filter(y -> y.getYear() == year)
        .findFirst()
        .orElseThrow(() -> EntityNotFoundException.year(year, dto.getUsername()));

    var trainingMonth = trainingYear.getMonths().stream()
        .filter(m -> m.getMonth() == month)
        .findFirst()
        .orElseThrow(() -> EntityNotFoundException.month(month, dto.getUsername()));

    subtractSummaryDuration(trainingMonth, dto.getTrainingDuration());
    dao.update(storedSummary);
    log.info("Deleted training duration:{} from {}.{} for trainer:{}", dto.getTrainingDuration(), year, month,
        dto.getUsername());
  }


  private TrainingSummary processEventOnMissingSummary(RegisterTrainingEventDto dto) {
    if (dto.getActionType().equals(ActionType.DELETE)) {
      throw EntityNotFoundException.summary(dto.getUsername());
    }
    log.info("Creating new summary for:{}", dto.getUsername());
    return dao.save(toTrainingSummary(dto));
  }

  private TrainingMonth getMonth(TrainingYear trainingYear, int month) {
    return trainingYear.getMonths().stream()
        .filter(m -> m.getMonth() == month)
        .findFirst()
        .orElseGet(() -> {
          var m = TrainingMonth.of(month);
          trainingYear.getMonths().add(m);
          return m;
        });
  }

  private TrainingYear getYear(TrainingSummary storedSummary, int year) {
    return storedSummary.getYears().stream()
        .filter(y -> y.getYear() == year)
        .findFirst()
        .orElseGet(() -> {
          var y = TrainingYear.of(year);
          storedSummary.getYears().add(y);
          return y;
        });
  }

  private TrainingSummary toTrainingSummary(RegisterTrainingEventDto dto) {
    return TrainingSummary.builder()
        .id(UUID.randomUUID())
        .username(dto.getUsername())
        .firstname(dto.getFirstname())
        .lastname(dto.getLastname())
        .isActive(dto.isActive())
        .years(new ArrayList<>())
        .build();
  }


  private TrainerStatisticsDto toTrainerStatisticsDto(TrainingSummary summary) {
    var statistic = summary.getYears().stream()
        .collect(Collectors.toMap(
            TrainingYear::getYear,
            year -> year.getMonths().stream()
                .collect(Collectors.toMap(
                    TrainingMonth::getMonth,
                    TrainingMonth::getSummaryDuration
                ))
        ));

    return TrainerStatisticsDto.builder()
        .username(summary.getUsername())
        .firstname(summary.getFirstname())
        .lastname(summary.getLastname())
        .isActive(summary.isActive())
        .duration(statistic)
        .build();
  }

  public void subtractSummaryDuration(TrainingMonth m, long trainingDuration) {
    if (m.getSummaryDuration() < trainingDuration) {
      log.error("Total training duration:{} is less than the deleted training duration:{}", m.getSummaryDuration(),
          trainingDuration);
      throw new IllegalArgumentException("Total training duration cannot be less than the deleted training duration");
    }
    m.setSummaryDuration(m.getSummaryDuration() - trainingDuration);
  }
}
