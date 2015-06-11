Feature: Logout

  @LoggedIn
  Scenario: Log out of the application
    Given I am on the "home" page
    When I log out
    Then I should be on the "home" page
    And I should not be logged in

