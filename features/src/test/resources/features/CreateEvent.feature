Feature: Create an event

  @LoggedIn
  Scenario: Create event with a description
    Given I am on the "create-event" page
    When I submit event details
    Then I should see the event in the events list

  @LoggedIn
  Scenario: Created events should be assigned to a user
    Given other user has events
    When I am on the "home" page
    Then I should have 0 events
