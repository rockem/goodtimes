Feature: Email Login

  Scenario: Login with username/password
    Given I'm not logged in
    And I'm a registered user
    When I am on the home page
    And I submit my credentials
    Then I should be logged in