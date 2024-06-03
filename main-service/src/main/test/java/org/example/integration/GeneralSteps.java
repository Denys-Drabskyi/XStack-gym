package org.example.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RequiredArgsConstructor
public class GeneralSteps {
  private final StepsContainer stepsContainer;
  private final ObjectMapper objectMapper;

  @Given("the application is running")
  public void theApplicationIsRunning() {
    // No action required, assuming the application is already running
  }
}
