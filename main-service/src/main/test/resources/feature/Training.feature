Feature: Training Management
  Scenario: Get training types
      Given the application is running
      When I send a "Get" request to "/training/type"
      Then the response status should be 403

  Scenario: Get training types
    Given the application is running
    When I send a "Get" request to "/training/type" with token "trainer.trainer"
    Then the response status should be 200
    And the response should be a List<TrainingType> object with "3" items