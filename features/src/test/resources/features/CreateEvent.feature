Feature: Create an event

  @LoggedIn
  Scenario: Create event with a description
    Given I am on the "create-event" page
    And I have no events
    When I submit event details
    Then I should see the event in the events list

