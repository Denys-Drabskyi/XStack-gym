Feature: User Management
  Scenario: login user succ
    Given the application is running
    And a trainee with username "trainee.trainee" exists
    When I send a "Get" request to "/user/login" with the following "UserCredentialsDto"
           | field     | value           |
           | username  | trainee.trainee |
           | password  | YAgU86xxdBEhKA  |
    Then the response status should be 200
    And the response should be a String object

  Scenario: login user fail
    Given the application is running
    And a trainee with username "trainee.trainee" exists
    When I send a "Get" request to "/user/login" with the following "UserCredentialsDto"
           | field     | value           |
           | username  | trainee.trainee |
           | password  | wrong6xxdBEhKA  |
    Then the response status should be 500

  Scenario: logout
    Given the application is running
    And a trainee with username "trainee.trainee" exists
    When I send a "Post" request to "/user/logout" with token "trainee.trainee"
    And I send a "GET" request to "/trainee/trainee.trainee" with token "trainee.trainee"
    Then the response status should be 403

