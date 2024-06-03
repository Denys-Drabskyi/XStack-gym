Feature: Trainer Management
  Scenario: Register a new trainer
      Given the application is running
      And a trainer with username "trainerfname.trainerLname" does not exist
      When I send a "POST" request to "/trainer/register" with the following "TrainerDto"
        | field          | value        |
        | firstName      | trainerfname |
        | lastName       | trainerLname |
        | username       | username     |
        | specialization | cardio       |
      Then the response status should be 404

  Scenario: Register a new trainer
    Given the application is running
    And a trainer with username "trainerfname.trainerLname" does not exist
    When I send a "POST" request to "/trainer/register" with the following "TrainerDto"
      | field          | value        |
      | firstName      | trainerfname |
      | lastName       | trainerLname |
      | username       | username     |
      | specialization | Cardio       |
    Then the response status should be 201
    And the response should be a UserCredentialsDto object with username is "trainerfname.trainerLname"

  Scenario: Register a trainer with existing username
    Given the application is running
    And a trainer with username "trainerfname.trainerLname" exists
    When I send a "POST" request to "/trainer/register" with the following "TrainerDto"
      | field          | value        |
      | firstName      | trainerfname |
      | lastName       | trainerLname |
      | username       | username     |
      | specialization | Cardio       |
    Then the response status should be 201
    And the response should be a UserCredentialsDto object with username is "trainerfname.trainerLname1"

  Scenario: Get a trainer by username
    Given the application is running
    And a trainer with username "trainerfname.trainerLname" exists
    When I send a "GET" request to "/trainer/trainerfname.trainerLname" with token "trainerfname.trainerLname"
    Then the response status should be 200
    And the response should be a TrainerDtoWithTrainees object

  Scenario: Get a trainer with non-existing username
    Given the application is running
    And a trainer with username "nonexistentuser" does not exist
    When I send a "GET" request to "/trainer/nonexistentuser" with token "trainerfname.trainerLname"
    Then the response status should be 404

  Scenario: Update a trainer
    Given the application is running
    And a trainer with username "trainerfname.trainerLname" exists
    When I send a "PUT" request to "/trainer" with the following "TrainerDto" and token "trainerfname.trainerLname"
     | field          | value                     |
     | firstName      | newtrainerfname           |
     | lastName       | newtrainerLname           |
     | username       | trainerfname.trainerLname |
     | specialization | Yoga                      |
    Then the response status should be 200
    And the response should be a TrainerDtoWithTrainees object

  Scenario: Get trainings with trainee.trainee between 2000-01-01 and 3000-01-01
      Given the application is running
      And a trainer with username "trainer.trainer" exists
      When I send a "Get" request to "/trainer/trainings?trainees=trainee.trainee&from=2000-01-01&to=3000-01-01" with token "trainer.trainer"
      Then the response status should be 200
      And the response should be a List<TrainingDto> object with "2" training

  Scenario: Get trainings with trainer.trainer between 2000-01-01 and 3000-01-01
      Given the application is running
      And a trainer with username "trainer.trainer" exists
      When I send a "Get" request to "/trainer/trainings?trainees=trainee.trainee&from=2000-01-01&to=2024-04-11" with token "trainer.trainer"
      Then the response status should be 200
      And the response should be a List<TrainingDto> object with "1" training
