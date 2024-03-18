package org.example.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.UUID;
import org.example.entity.TrainingType;
import org.example.repository.TrainingTypeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TrainingTypeServiceImplTest {

  @Mock
  private TrainingTypeRepository repository;

  @InjectMocks
  private TrainingTypeServiceImpl trainingTypeService;

  @Test
  @DisplayName("getByName(collection<string>) test")
  void testCase01() {
    ArrayList<String> names = new ArrayList<>();
    when(repository.getByNameIn(any())).thenReturn(new ArrayList<>());

    trainingTypeService.getByName(names);
    verify(repository, times(1)).getByNameIn(names);
  }

  @Test
  @DisplayName("getByName(string) test")
  void testCase02() {
    String name = "";
    when(repository.getByName(any())).thenReturn(new TrainingType(UUID.randomUUID(), "test"));

    trainingTypeService.getByName(name);
    verify(repository, times(1)).getByName(name);
  }
}