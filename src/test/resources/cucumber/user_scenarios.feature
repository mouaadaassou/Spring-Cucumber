Feature: Checking User Service
  Scenario: 1 - Find user with id 3
    When GET /3 is called
    Then a user with httpCode 200 and response should be JSON:
      """
      {
        "firstName": "firstName-3",
        "lastName":"lastName-3",
        "email":"firstName_3@gmail.com"
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

  Scenario: 3 - Find all users in the DB
    Given we have 10 users in the DB
    When GET / is called
    Then an httpCode 200 with response should be JSON:
    """
    [
      {
        "firstName": "firstName-1",
        "lastName": "lastName-1",
        "email":"firstName_1@gmail.com"
      },
      {
        "firstName": "firstName-2",
        "lastName": "lastName-2",
        "email":"firstName_2@gmail.com"
      },
      {
        "firstName": "firstName-3",
        "lastName": "lastName-3",
        "email":"firstName_3@gmail.com"
      },
      {
        "firstName": "firstName-4",
        "lastName": "lastName-4",
        "email":"firstName_4@gmail.com"
      },
      {
        "firstName": "firstName-5",
        "lastName": "lastName-5",
        "email":"firstName_5@gmail.com"
      },
      {
        "firstName": "firstName-6",
        "lastName": "lastName-6",
        "email":"firstName_6@gmail.com"
      },
      {
        "firstName": "firstName-7",
        "lastName": "lastName-7",
        "email":"firstName_7@gmail.com"
      },
      {
        "firstName": "firstName-8",
        "lastName": "lastName-8",
        "email":"firstName_8@gmail.com"
      },
      {
        "firstName": "firstName-9",
        "lastName": "lastName-9",
        "email":"firstName_9@gmail.com"
      },
      {
        "firstName": "firstName-10",
        "lastName": "lastName-10",
        "email":"firstName_10@gmail.com"
      }
    ]
    """
    Scenario: 4 - Listing all the http operations supported by the service
      When OPTIONS / is called
      Then an http status of 200 is returned along with allow header:
      """
      GET,POST,DELETE,OPTIONS
      """
