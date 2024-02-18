package org.example.util;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import lombok.SneakyThrows;
import org.example.configuration.properties.DaoFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GeneralStorageTest {

  @Mock
  private DaoFile daoFile;
  @Mock
  private ObjectMapper objectMapper;

  @InjectMocks
  private GeneralStorage storage;

  @BeforeEach
  void setUp() {
    when(daoFile.getTrainee()).thenReturn("");
    when(daoFile.getTraining()).thenReturn("");
    when(daoFile.getTrainer()).thenReturn("");
    when(daoFile.getUser()).thenReturn("");
  }


  @Test
  @DisplayName("postConstruct() tries to load 4 storages")
  @SneakyThrows
  void testCase01() {
    when(objectMapper.readValue((File) any(), (TypeReference<Object>) any())).thenReturn(null);
    storage.postConstruct();
    verify(objectMapper, times(4)).readValue((File) any(), (TypeReference<Object>) any());
  }

  @Test
  @DisplayName("preDestroy() tries to write 4 storages")
  @SneakyThrows
  void testCase02() {
    doNothing().when(objectMapper).writeValue((File) any(), any());
    storage.preDestroy();
    verify(objectMapper, times(4)).writeValue((File) any(), any());
  }
}