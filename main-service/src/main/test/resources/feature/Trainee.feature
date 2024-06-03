Feature: Trainee Management
  Scenario: Register a new trainee
    Given the application is running
    And a trainee with username "firstname.lastname" does not exist
    When I send a "POST" request to "/trainee/register" with the following "TraineeDto"
      | field     | value       |
      | firstName | firstname   |
      | lastName  | lastname    |
      | username  | username    |
      | address   | address     |
      | birthDate | 1990-01-01  |
    Then the response status should be 201
    And the response should be a UserCredentialsDto object with username is "firstname.lastname"

  Scenario: Register a trainee with existing username
    Given the application is running
    And a trainee with username "trainee.trainee" exists
    When I send a "POST" request to "/trainee/register" with the following "TraineeDto"
      | field     | value       |
      | firstName | trainee     |
      | lastName  | trainee     |
      | username  | username    |
      | address   | address     |
      | birthDate | 1990-01-01  |
    Then the response status should be 201
    And the response should be a UserCredentialsDto object with username is "trainee.trainee1"

  Scenario: Get a trainee by username
    Given the application is running
    And a trainee with username "firstname.lastname" exists
    When I send a "GET" request to "/trainee/firstname.lastname" with token "firstname.lastname"
    Then the response status should be 200
    And the response should be a TraineeDtoWithTrainers object

  Scenario: Get a trainee with non-existing username
    Given the application is running
    And a trainee with username "nonexistentuser" does not exist
    When I send a "GET" request to "/trainee/nonexistentuser" with token "firstname.lastname"
    Then the response status should be 404

  Scenario: Update another trainee
    Given the application is running
    And a trainee with username "firstname.lastname" exists
    When I send a "PUT" request to "/trainee" with the following "TraineeDto" and token "firstname.lastname"
     | field     | value           |
     | firstName | newfirstname    |
     | lastName  | newlastname     |
     | username  | trainee.trainee |
     | address   | newaddress      |
     | birthDate | 1991-01-01      |
    Then the response status should be 500

  Scenario: Update a trainee
      Given the application is running
      And a trainee with username "firstname.lastname" exists
      When I send a "PUT" request to "/trainee" with the following "TraineeDto" and token "firstname.lastname"
       | field     | value              |
       | firstName | newfirstname       |
       | lastName  | newlastname        |
       | username  | firstname.lastname |
       | address   | newaddress         |
       | birthDate | 1991-01-01         |
      Then the response status should be 200
      And the response should be a TraineeDtoWithTrainers object

  Scenario: Delete a trainee
    Given the application is running
    And a trainee with username "firstname.lastname" exists
    When I send a "DELETE" request to "/trainee" with token "firstname.lastname"
    Then the response status should be 200

  Scenario: Get not assigned active trainers
    Given the application is running
    And a trainee with username "trainee.trainee" exists
    When I send a "Get" request to "/trainee/trainer" with token "trainee.trainee"
    Then the response status should be 200
    And the response should be a List<TrainerDto> object with "2" trainers

  Scenario: Update a trainers
    Given the application is running
    And a trainee with username "trainee.trainee" exists
    When I send a "PUT" request to "/trainee/trainer" with the following "UpdateTrainersListDto" and token "trainee.trainee"
     | Trainers       |
     | trainer.train  |
     | trainer.train1 |
     | trainer.train2 |
    Then the response status should be 200
    And the response should be a List<TrainerDto> object with "2" trainers

  Scenario: Get not assigned active trainers after adding
    Given the application is running
    And a trainee with username "trainee.trainee" exists
    When I send a "Get" request to "/trainee/trainer" with token "trainee.trainee"
    Then the response status should be 200
    And the response should be a List<TrainerDto> object with "1" trainers
    # in test data trainee.trainee had trainer.trainer as a trainer, after previous step he lost trainer.trainer as a trainer

  Scenario: Get trainings with trainer.trainer and trainer.train between 2000-01-01 and 3000-01-01
      Given the application is running
      And a trainee with username "trainee.trainee" exists
      When I send a "Get" request to "/trainee/trainings?trainers=trainer.trainer,trainer.train&from=2000-01-01&to=3000-01-01" with token "trainee.trainee"
      Then the response status should be 200
      And the response should be a List<TrainingDto> object with "3" training

  Scenario: Get trainings with trainer.trainer between 2000-01-01 and 3000-01-01
      Given the application is running
      And a trainee with username "trainee.trainee" exists
      When I send a "Get" request to "/trainee/trainings?trainers=trainer.trainer&from=2000-01-01&to=3000-01-01" with token "trainee.trainee"
      Then the response status should be 200
      And the response should be a List<TrainingDto> object with "2" training

  Scenario: Get trainings with trainer.trainer between 2000-01-01 and 2024-04-10
        Given the application is running
        And a trainee with username "trainee.trainee" exists
        When I send a "Get" request to "/trainee/trainings?trainers=trainer.trainer&from=2000-01-01&to=2024-04-11" with token "trainee.trainee"
        Then the response status should be 200
        And the response should be a List<TrainingDto> object with "1" training