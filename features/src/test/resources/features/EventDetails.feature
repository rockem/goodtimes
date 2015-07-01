Feature: Event dashboard

  @LoggedIn
  Scenario: Show event dashboard
    Given I have events
    And I am on the "home" page
    When I click on an event
    Then I should be on the "event's details" page
    And I should see the event details