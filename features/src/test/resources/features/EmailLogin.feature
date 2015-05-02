Feature: Email Login

  @wip
  Scenario: Login with username/password
    Given I'm not logged in
    And I'm a registered user
    When I am on the "login" page
    And I submit my credentials
    Then I should be on the "home" page
    And I should be logged in
