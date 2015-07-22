Feature: Signup

  @wip
  Scenario: Signup to Goodtimes
    Given I'm not logged in
    When I click on "Sign up"
    And I submit Poppi's registration information
    Then I should be on the "home" page
    And I should be logged in as "Poppi"
