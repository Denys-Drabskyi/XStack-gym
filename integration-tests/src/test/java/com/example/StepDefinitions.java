package com.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions extends GenericTest {

  @Given("an example scenario")
  public void anExampleScenario() {
    while (true) {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  @When("all step definitions are implemented")
  public void allStepDefinitionsAreImplemented() {
  }

  @Then("the scenario passes")
  public void theScenarioPasses() {
  }

}
