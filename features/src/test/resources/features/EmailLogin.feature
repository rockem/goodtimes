Feature: Email Login

  Scenario: Login with username/password
    Given I'm not logged in
    When I log in
    Then I should be on the "home" page
    And I should be logged in
