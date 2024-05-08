package com.example.trainersservice.service;

import com.example.trainersservice.TrainingSummaryNotFoundException;
import com.example.trainersservice.dao.TrainingSummaryDao;
import com.example.trainersservice.entity.TrainingMonth;
import com.example.trainersservice.entity.TrainingSummary;
import com.example.trainersservice.entity.TrainingYear;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.ActionType;
import org.example.dto.RegisterTrainingEventDto;
import org.example.dto.TrainerStatisticsDto;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@Profile("mongo")
@RequiredArgsConstructor
public class TrainingSummaryService implements TrainerService {
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
        var trainingYear = storedSummary.getYears().stream()
                .filter(y -> y.getYear() == year)
                .findFirst()
                .orElseGet(() -> {
                    var y = TrainingYear.of(year);
                    storedSummary.getYears().add(y);
                    return y;
                });
        var trainingMonth = trainingYear.getMonths().stream()
                .filter(m -> m.getMonth() == month)
                .findFirst()
                .orElseGet(() -> {
                    var m = TrainingMonth.of(month);
                    trainingYear.getMonths().add(m);
                    return m;
                });
        trainingMonth.setSummaryDuration(trainingMonth.getSummaryDuration() + dto.getTrainingDuration());
        dao.update(storedSummary);
    }

    private void deleteTraining(RegisterTrainingEventDto dto, int year, int month) {
        var storedSummary = dao.getByTrainerUsername(dto.getUsername())
                .orElseGet(() -> processEventOnMissingSummary(dto));
        var trainingYear = storedSummary.getYears().stream()
                .filter(y -> y.getYear() == year)
                .findFirst();

        Optional<TrainingMonth> trainingMonth = Optional.empty();
        if (trainingYear.isPresent()) {
            trainingMonth = trainingYear.get().getMonths().stream()
                    .filter(m -> m.getMonth() == month)
                    .findFirst();
        }

        trainingMonth.ifPresent(m -> subtractSummaryDuration(m, dto.getTrainingDuration()));
        dao.update(storedSummary);
    }



    private TrainingSummary processEventOnMissingSummary(RegisterTrainingEventDto dto) {
        if (dto.getActionType().equals(ActionType.DELETE)) {
            throw TrainingSummaryNotFoundException.withUsername(dto.getUsername());

        }
        return dao.save(toTrainingSummary(dto));
    }

    private TrainingSummary toTrainingSummary(RegisterTrainingEventDto dto) {
        return TrainingSummary.builder()
                .id(UUID.randomUUID())
                .username(dto.getUsername())
                .firstname(dto.getFirstname())
                .lastname(dto.getLastname())
                .isActive(dto.isActive())
                .years(List.of())
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
            throw new IllegalArgumentException("Training duration cannot be less than the total training duration");
        }
        m.setSummaryDuration(m.getSummaryDuration() - trainingDuration);
    }
}
