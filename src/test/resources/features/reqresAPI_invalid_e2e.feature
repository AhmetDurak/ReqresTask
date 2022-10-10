@wip
Feature: Test REQRES REST-API with INVALID DATA
  Users should be able to submit GET, POST, PATCH, DELETE requests to a web service, represented by reqres.in
  Users shouldn't submit requests, or get failure as response with the given invalid data

  #INVALID SCENARIOS
  Scenario: GET USER LIST from database
    When I send GET request to endpoint with the following page number 1
    Then I get user list
    And status code should be 200

  Scenario: GET INVALID SINGLE USER data from database
    When I send GET request to endpoint with the following id number: 13
    Then I get the registered user informations
      | id | email | first_name | last_name |
      |    |       |            |           |
    And status code should be 404

  Scenario: REGISTER with RANDOM user informations
    When I send POST request to endpoint with RANDOM user informations
    Then status code should be 400
    And verify data does NOT have any error

  Scenario Outline: CREATE a user with INVALID body
    When I send POST request to endpoint with INVALID '<name>' and '<job>'
    Then status code should be 400

    Examples: Testing invalid body inputs
      | name   | job    |
      | 123123 | 123124 |
      | &/%$&/ | &%$$   |
      |        |        |
    #body is also accepting empty input

  #Because PUT accepts any empty or non-empty input, not possible to apply any invalid data
  #As Header Authorization accepts also any value regardless from retrieved token
  Scenario: UPDATE created user information
    When I send PUT request to endpoint with created user id
    Then status code should be 200
    And I get user informations

  Scenario: DELETE created user and try to retrieve the same user data
    When I send DELETE request to endpoint with created user id
    Then status code should be 204
    #Due to possible bug about retrieving single user data following code doesn't show real results
    And I send GET request to endpoint with created user id
    Then status code should be 404


