Feature: Checking User Service
  Scenario: 1 - Find user with id 3
    When GET /3 is called
    Then a user with httpCode 200 and response should be JSON:
      """
      {
        "firstName": "firstName-3",
        "lastName":"lastName-3"
      }
      """

  Scenario: 2 - Find a user that does not exists
    Given we have 10 user in the DB
    When GET /14 is called
    Then an httpCode 404 and response should be JSON:
      """
      {
        "httpStatus": "NOT_FOUND",
        "errorMessage": "User with id 14 NOT FOUND",
        "logRef": 14
      }
      """
