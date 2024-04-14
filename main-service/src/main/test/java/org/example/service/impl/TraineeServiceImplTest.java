package org.example.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.example.dao.TraineeDao;
import org.example.dto.TraineeDto;
import org.example.dto.TraineeDtoWithTrainers;
import org.example.dto.UserDto;
import org.example.entity.Trainee;
import org.example.entity.User;
import org.example.mapper.TraineeMapper;
import org.example.repository.TraineeRepository;
import org.example.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class TraineeServiceImplTest {

  private TraineeDto TRAINEE_DTO;

  @Autowired
  private TraineeRepository repository;
  @Autowired
  private TraineeServiceImpl service;

  @BeforeEach
  void setUp() {
    TRAINEE_DTO = TraineeDto.builder()
        .firstName("trainee")
        .lastName("trainee")
        .password("test")
        .birthDate(LocalDate.MIN)
        .address("address")
        .build();
  }

  @Test
  @DisplayName("getWithTrainers() test")
  void testCase01() {
    TraineeDtoWithTrainers rez = service.getWithTrainers("trainee.trainee");
    assertEquals("trainee", rez.getFirstName());
    assertEquals(1, rez.getTrainers().size());
  }

  @Test
  @DisplayName("create() test")
  void testCase02() {
    TraineeDto rez = service.create(TRAINEE_DTO);
    assertEquals("trainee.trainee1", rez.getUsername());
    assertNotNull(rez.getPassword());
  }

  @Test
  @DisplayName("update() test")
  void testCase03() {
    TRAINEE_DTO.setUsername("trainee.trainee");
    TRAINEE_DTO.setFirstName("new");
    TRAINEE_DTO.setAddress("new");

    TraineeDto rez = service.update(TRAINEE_DTO);
    assertEquals("trainee.trainee", rez.getUsername());
    assertEquals("new", rez.getFirstName());
    assertEquals("new", rez.getAddress());
    assertEquals("trainee", rez.getLastName());
  }

  @Test
  @DisplayName("deleteByUsername() test")
  void testCase04() {
    TRAINEE_DTO.setUsername("trainee.trainee");
    assertEquals(1, repository.findAll().size());
    service.deleteByUsername(TRAINEE_DTO.getUsername());
    assertEquals(0, repository.findAll().size());
  }

}