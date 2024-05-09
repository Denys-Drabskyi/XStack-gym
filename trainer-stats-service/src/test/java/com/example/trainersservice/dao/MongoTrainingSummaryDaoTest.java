package com.example.trainersservice.dao;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.trainersservice.entity.TrainingSummary;
import com.example.trainersservice.repository.mongo.TrainingSummaryRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("mongo")
@ExtendWith(MockitoExtension.class)
class MongoTrainingSummaryDaoTest {
  @Autowired
  MongoTrainingSummaryDao mongoTrainingSummaryDao;

  @MockBean
  TrainingSummaryRepository repository;

  TrainingSummary trainingSummary = TrainingSummary.builder().build();

  @Test
  void save() {
    when(repository.insert(trainingSummary)).thenReturn(trainingSummary);
    mongoTrainingSummaryDao.save(trainingSummary);
    verify(repository, times(1)).insert(trainingSummary);
  }

  @Test
  void update() {
    when(repository.save(trainingSummary)).thenReturn(trainingSummary);
    mongoTrainingSummaryDao.update(trainingSummary);
    verify(repository, times(1)).save(trainingSummary);
  }

  @Test
  void getByTrainerUsername() {
    when(repository.findByUsername(any())).thenReturn(Optional.of(trainingSummary));
    mongoTrainingSummaryDao.getByTrainerUsername("trainingSummary");
    verify(repository, times(1)).findByUsername(any());
  }
}