package org.example.integration.steps.trainer;

import io.cucumber.java.en.Given;
import lombok.RequiredArgsConstructor;
import org.example.repository.TrainerRepository;

@RequiredArgsConstructor
public class TrainerSteps {
  private final TrainerRepository trainerRepository;

  @Given("a trainer with username {string} does not exist")
  public void a_trainer_with_username_does_not_exist(String string) {
    trainerRepository.getByUserUsername(string)
        .ifPresent((p) -> {
          throw new RuntimeException("Username " + string + " already exists");
        });
  }

  @Given("a trainer with username {string} exists")
  public void a_trainer_with_username_exists(String string) {
    trainerRepository.getByUserUsername(string)
        .orElseThrow(() -> new RuntimeException("Username " + string + " does not exist"));
  }
}
