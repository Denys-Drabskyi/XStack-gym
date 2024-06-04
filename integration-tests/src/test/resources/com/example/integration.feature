Feature: Trainer stats update

  Scenario: Get trainer stats for trainer.trainer should return initial db values
    Given services are running
    When I send a "GET" request to "http://localhost:8881/trainer/trainer.trainer"
    Then the response status should be 200
    And response should have
      | year | month | value |
      | 2030 | 1     | 3600  |
      | 2030 | 2     | 3600  |
      | 2030 | 3     | 3600  |
      | 2030 | 4     | 3600  |
      | 2031 | 1     | 3600  |
      | 2032 | 1     | 3600  |
      | 2033 | 1     | 3600  |

  Scenario: create training succ
    Given services are running
    When I send a "POST" request to "http://localhost:8880/training" with the following "TrainingDto" and token "trainee.trainee"
      | field           | value           |
      | traineeUsername | trainee.trainee |
      | trainerUsername | trainer.trainer |
      | name            | training name   |
      | trainingType    | Cardio          |
      | date            | 2030-01-01      |
      | duration        | 3600            |
    Then the response status should be 200
    And response of "GET" request to "http://localhost:8881/trainer/trainer.trainer" should have time
      | year | month | value |
      | 2030 | 1     | 7200  |
      | 2030 | 2     | 3600  |
      | 2030 | 3     | 3600  |
      | 2030 | 4     | 3600  |
      | 2031 | 1     | 3600  |
      | 2032 | 1     | 3600  |
      | 2033 | 1     | 3600  |

  Scenario: delete training succ
    Given services are running
    When I send a "DELETE" request to "http://localhost:8880/training/550e8400-e29b-41d4-a716-446655440012" with token "trainee.trainee"
    Then the response status should be 200
    And response of "GET" request to "http://localhost:8881/trainer/trainer.trainer" should have time
      | year | month | value |
      | 2030 | 1     | 7200  |
      | 2030 | 2     | 3600  |
      | 2030 | 3     | 3600  |
      | 2030 | 4     | 3600  |
      | 2031 | 1     | 3600  |
      | 2032 | 1     | 3600  |
      | 2033 | 1     | 0     |