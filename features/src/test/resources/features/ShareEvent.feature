Feature: Share event

  @wip
  @LoggedIn
  Scenario: Share event with a registered user
    Given I've created an event
    And I'm on the event's details page
    And "kuku" is a registered user
    When I share my event with "kuku"
    Then "kuku" should have the event as well
