Feature: Create an event

  Scenario: Create event with a description
    Given I am on the home page
    When I submit event details
    Then I should see the event in the events list

