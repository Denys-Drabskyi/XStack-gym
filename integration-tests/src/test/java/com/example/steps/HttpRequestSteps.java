package com.example.steps;

import static org.junit.Assert.assertEquals;

import com.example.Storage;
import com.example.dto.TrainerStatisticsDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import lombok.SneakyThrows;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class HttpRequestSteps {
  Storage storage = Storage.getInstance();

  private static final String[] SERVICES = {
      "http://localhost:8880/actuator/health", // main-service
      "http://localhost:8881/actuator/health", // trainer-stats-service
      "http://localhost:8161", // activemq
  };

  private static final String traineeTraineeInfiniteToken =
      "eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJzdWIiOiJ0cmFpbmVlLnRyYWluZWUiLCJleHAiOjE3MTcxODgwODkwMCwiaWF0IjoxNzE3MTAxNjg5MDAsInVzZXJuYW1lIjoidHJhaW5lZS50cmFpbmVlIn0.XdFAlxD-GzCDCwIM8z1o0Dd7HFqIe8o70h0Bm2HZ_oI";
  private static final String trainerTrainerInfiniteToken =
      "eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJzdWIiOiJ0cmFpbmVyLnRyYWluZXIiLCJleHAiOjE3MTcxODgwODkwMCwiaWF0IjoxNzE3MTAxNjg5MDAsInVzZXJuYW1lIjoidHJhaW5lci50cmFpbmVyIn0.DkdCm-e3cptKJPIKUjEdOAjkX6w6TMdFvo8NOKFnyNQ";
  private static final String firstnameLastNameInfiniteToken =
      "eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJzdWIiOiJmaXJzdG5hbWUubGFzdG5hbWUiLCJleHAiOjE3MTcxODgwODkwMCwiaWF0IjoxNzE3MTAxNjg5MDAsInVzZXJuYW1lIjoiZmlyc3RuYW1lLmxhc3RuYW1lIn0.Yw_U9zoYFKSp--31ZYEYonqzI1V5tkfYCJXsmBiTa2E";
  private static final String trainerfnameTrainerLname =
      "eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJzdWIiOiJ0cmFpbmVyZm5hbWUudHJhaW5lckxuYW1lIiwiZXhwIjoxNzE3MTg4MDg5MDAsImlhdCI6MTcxNzEwMTY4OTAwLCJ1c2VybmFtZSI6InRyYWluZXJmbmFtZS50cmFpbmVyTG5hbWUifQ.iP81MtEoYDeY-3eAqrfqxDmGeebQFyy_7Qgm1_BNxsc";

  @Given("services are running")
  public void services_are_running() {
    for (String serviceUrl : SERVICES) {
      request("get", serviceUrl);
      responseCode(200);
    }
  }

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
    HttpUriRequest request = getMethod(methodName.toLowerCase(Locale.ROOT), endpoint);
    if (!token.isEmpty()) {
      request.addHeader("Authorization", "Bearer " + getToken(token));
    }
    if (!objectName.isEmpty()) {
      storage.body = BodyBuilder.objectOf(objectName, dataTable);
      StringEntity entity = new StringEntity(storage.mapper.writeValueAsString(storage.body));
      entity.setContentType("application/json");
      request.setHeader("Content-Type", "application/json");
      request.setHeader("Accept", "application/json");
      ((HttpEntityEnclosingRequestBase) request).setEntity(entity);
    }
    HttpClient httpClient = HttpClientBuilder.create().build();
    storage.rez = httpClient.execute(request);
  }

  @Then("the response status should be {int}")
  public void responseCode(Integer statusCode) {
    assertEquals((int) statusCode, storage.rez.getStatusLine().getStatusCode());
  }

  @SneakyThrows
  @Then("response should have")
  public void response_should_have(io.cucumber.datatable.DataTable dataTable) {
    var rez = storage.mapper.readValue(getResponseBody(storage.rez), TrainerStatisticsDto.class);
    assertEquals(BodyBuilder.objectOf("DurationMap", dataTable), rez.getDuration());
  }

  @SneakyThrows
  @Then("response of {string} request to {string} should have time")
  public void response_of_request_to_should_have_time(String string, String string2, io.cucumber.datatable.DataTable dataTable) {
    Thread.sleep(1000L); // to give mb some time
    request(string, string2);
    response_should_have(dataTable);
  }

  public HttpUriRequest getMethod(String methodName, String endpoint) {
    switch (methodName) {
      case "post" -> {
        return new HttpPost(endpoint);
      }
      case "get" -> {
        return new HttpGet(endpoint);
      }
      case "put" -> {
        return new HttpPut(endpoint);
      }
      case "delete" -> {
        return new HttpDelete(endpoint);
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

  @SneakyThrows
  public String getResponseBody(HttpResponse response) {
    HttpEntity entity = response.getEntity();
    if (entity != null) {
      return EntityUtils.toString(entity);
    }
    return "";
  }
}
