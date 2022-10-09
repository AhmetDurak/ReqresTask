Feature: Test REQRES REST-API
  Users should be able to submit GET, POST, PATCH, DELETE requests to a web service, represented by reqres.in

  Scenario: GET USER LIST from database
    When I send GET request to endpoint with the following page number 1
    Then I get user list
    And status code should be 200

  Scenario: GET SINGLE USER data from database
    When I send GET request to endpoint with the following id number: 1
    Then I get the registered user informations
      | id | email                  | first_name | last_name |
      | 1  | george.bluth@reqres.in | George     | Bluth     |
    And status code should be 200

  Scenario: REGISTER with previously specified user informations
    When I send POST request to endpoint with given user informations
    Then status code should be 200
    And verify data does NOT have any error

  Scenario: CREATE a user with the given token and verify status code
    When I send POST request to endpoint with generated name and job
    Then status code should be 201

  Scenario: UPDATE created user information
    When I send PUT request to endpoint with created user id
    Then status code should be 200
    And I get user informations

  Scenario: GET SINGLE USER data from database
    When I send GET request to endpoint with created user id
    Then status code should be 200
    And I get the created user informations

  Scenario: DELETE created user and verify status code
    When I send DELETE request to endpoint with created user id
    Then status code should be 204

