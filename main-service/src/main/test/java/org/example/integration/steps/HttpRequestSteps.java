package org.example.integration.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jakarta.ws.rs.core.MediaType;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.dto.TraineeDtoWithTrainers;
import org.example.dto.TrainerDto;
import org.example.dto.TrainerDtoWithTrainees;
import org.example.dto.TrainingDto;
import org.example.entity.TrainingType;
import org.example.integration.BodyBuilder;
import org.example.integration.StepsContainer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@RequiredArgsConstructor
public class HttpRequestSteps {
  private final StepsContainer stepsContainer;
  private final ObjectMapper objectMapper;
  private final MockMvc mockMvc;

  private static final String traineeTraineeInfiniteToken =
      "eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJzdWIiOiJ0cmFpbmVlLnRyYWluZWUiLCJleHAiOjE3MTcxODgwODkwMCwiaWF0IjoxNzE3MTAxNjg5MDAsInVzZXJuYW1lIjoidHJhaW5lZS50cmFpbmVlIn0.XdFAlxD-GzCDCwIM8z1o0Dd7HFqIe8o70h0Bm2HZ_oI";
  private static final String trainerTrainerInfiniteToken =
      "eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJzdWIiOiJ0cmFpbmVyLnRyYWluZXIiLCJleHAiOjE3MTcxODgwODkwMCwiaWF0IjoxNzE3MTAxNjg5MDAsInVzZXJuYW1lIjoidHJhaW5lci50cmFpbmVyIn0.DkdCm-e3cptKJPIKUjEdOAjkX6w6TMdFvo8NOKFnyNQ";
  private static final String firstnameLastNameInfiniteToken =
      "eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJzdWIiOiJmaXJzdG5hbWUubGFzdG5hbWUiLCJleHAiOjE3MTcxODgwODkwMCwiaWF0IjoxNzE3MTAxNjg5MDAsInVzZXJuYW1lIjoiZmlyc3RuYW1lLmxhc3RuYW1lIn0.Yw_U9zoYFKSp--31ZYEYonqzI1V5tkfYCJXsmBiTa2E";
  private static final String trainerfnameTrainerLname =
      "eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJzdWIiOiJ0cmFpbmVyZm5hbWUudHJhaW5lckxuYW1lIiwiZXhwIjoxNzE3MTg4MDg5MDAsImlhdCI6MTcxNzEwMTY4OTAwLCJ1c2VybmFtZSI6InRyYWluZXJmbmFtZS50cmFpbmVyTG5hbWUifQ.iP81MtEoYDeY-3eAqrfqxDmGeebQFyy_7Qgm1_BNxsc";

  @SneakyThrows
  @When("I send a {string} request to {string} with the following {string} and token {string}")
  public void requestWithBody(
      String methodName, String endpoint, String object, String token, DataTable dataTable) {
    request(methodName, endpoint, object, token, dataTable);
  }

  @SneakyThrows
  @When("I send a {string} request to {string} with the following {string}")
  public void requestWithBody(
      String methodName, String endpoint, String object, DataTable dataTable) {
    request(methodName, endpoint, object, "", dataTable);
  }

  @SneakyThrows
  @When("I send a {string} request to {string} with token {string}")
  public void request(String methodName, String endpoint, String token) {
    request(methodName, endpoint, "", token, null);
  }

  @SneakyThrows
  @When("I send a {string} request to {string}")
  public void request(String methodName, String endpoint) {
    request(methodName, endpoint, "", "", null);
  }

  @SneakyThrows
  public void request(String methodName, String endpoint, String objectName, String token, DataTable dataTable) {
    MockHttpServletRequestBuilder method = getMethod(methodName.toLowerCase(Locale.ROOT), endpoint);
    if (!token.isEmpty()) {
      method.header("Authorization", "Bearer " + getToken(token));
    }
    if (!objectName.isEmpty()) {
      stepsContainer.body = BodyBuilder.objectOf(objectName, dataTable);
      method.contentType(MediaType.APPLICATION_JSON)
          .content(objectMapper.writeValueAsString(stepsContainer.body));
    }
    stepsContainer.rez = mockMvc.perform(method)
        .andReturn();
  }

  @Then("the response status should be {int}")
  public void responseCode(Integer statusCode) {
    assertEquals((int) statusCode, stepsContainer.rez.getResponse().getStatus());
  }

  @Then("the response should be a List<TrainerDto> object with {string} trainers")
  public void the_response_should_be_a_object(String count) {
    try {
      var rez = objectMapper.readValue(stepsContainer.rez.getResponse().getContentAsString(),
          new TypeReference<List<TrainerDto>>() {
          });
      assertEquals(Integer.parseInt(count), rez.size());
    } catch (JsonProcessingException | UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
  }

  @Then("the response should be a List<TrainingDto> object with {string} training")
  public void the_response_should_be_a_list_training_dto_object_with_training(String count) {
    try {
      var rez = objectMapper.readValue(stepsContainer.rez.getResponse().getContentAsString(),
          new TypeReference<List<TrainingDto>>() {
          });
      assertEquals(Integer.parseInt(count), rez.size());
    } catch (JsonProcessingException | UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
  }

  @Then("the response should be a List<TrainingType> object with {string} items")
  public void the_response_should_be_a_list_training_type_object_with_items(String string) {
    try {
      var rez = objectMapper.readValue(stepsContainer.rez.getResponse().getContentAsString(),
          new TypeReference<List<TrainingType>>() {
          });
      assertEquals(Integer.parseInt(string), rez.size());
    } catch (JsonProcessingException | UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
  }

  @Then("the response should be a TraineeDtoWithTrainers object")
  public void the_response_should_be_a_trainee_dto_with_trainers_object() {
    try {
      objectMapper.readValue(stepsContainer.rez.getResponse().getContentAsString(), TraineeDtoWithTrainers.class);
    } catch (JsonProcessingException | UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
  }

  @Then("the response should be a TrainerDtoWithTrainees object")
  public void the_response_should_be_a_trainer_dto_with_trainees_object() {
    try {
      objectMapper.readValue(stepsContainer.rez.getResponse().getContentAsString(), TrainerDtoWithTrainees.class);
    } catch (JsonProcessingException | UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
  }

  @Then("the response should be a String object")
  public void the_response_should_be_a_string_object() {
    try {
      if (stepsContainer.rez.getResponse().getContentAsString().length() != 201) {
        throw new RuntimeException("response content length should be 201");
      }
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
  }

  public MockHttpServletRequestBuilder getMethod(String methodName, String endpoint) {
    switch (methodName) {
      case "post" -> {
        return post(endpoint);
      }
      case "get" -> {
        return get(endpoint);
      }
      case "put" -> {
        return put(endpoint);
      }
      case "delete" -> {
        return delete(endpoint);
      }
      default -> throw new IllegalStateException("Unexpected value: " + methodName);
    }
  }

  public String getToken(String token) {
    switch (token) {
      case "trainee.trainee" -> {
        return traineeTraineeInfiniteToken;
      }
      case "trainer.trainer" -> {
        return trainerTrainerInfiniteToken;
      }
      case "firstname.lastname" -> {
        return firstnameLastNameInfiniteToken;
      }
      case "trainerfname.trainerLname" -> {
        return trainerfnameTrainerLname;
      }
      default -> throw new IllegalStateException("Unexpected value: " + token);
    }
  }
}
