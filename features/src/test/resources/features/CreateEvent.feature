Feature: Create an event

  @LoggedIn
  Scenario: Create event with a description
    Given I am on the "home" page
    When I click on create event
    And I submit event details
    Then I should be on the "home" page
    And I should see the event in the events list

  @LoggedIn
  Scenario: Created events should be assigned to a user
    Given other user has events
    When I am on the "home" page
    Then I should have 0 events
