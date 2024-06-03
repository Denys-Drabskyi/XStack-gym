package org.example.integration.steps.trainee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.integration.StepsContainer;
import org.example.repository.TraineeRepository;

@RequiredArgsConstructor
public class TraineeSteps {
  private final StepsContainer stepsContainer;
  private final TraineeRepository traineeRepository;
  private final ObjectMapper objectMapper;

  @Given("a trainee with username {string} does not exist")
  public void aTraineeWithUsernameDoesNotExist(String username) {
    traineeRepository.getByUserUsername(username).ifPresent((p) -> {
          throw new RuntimeException("Username " + username + " already exists");
        }
    );
  }

  @Given("a trainee with username {string} exists")
  public void a_trainee_with_username_exists(String string) {
    traineeRepository.getByUserUsername(string).orElseThrow(
        () -> new RuntimeException("Username " + string + " does not exist")
    );
  }

  @SneakyThrows
  @Then("the response should be a UserCredentialsDto object with username is {string}")
  public void the_response_should_be_a_user_credentials_dto_object_with_username_is_firstname_lastname(String string) {
    DocumentContext jsonContext = JsonPath.parse(stepsContainer.rez.getResponse().getContentAsString());
    String username = jsonContext.read("$.username");
    System.out.println(username);
    assertEquals(string, username);
  }


}

