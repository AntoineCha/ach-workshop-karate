Feature: create a cart

  Scenario: create a cart
    Given url BASE_URL
    And header Content-Type = 'application/json'
    And request {}
    When method post
    * def id = response.result.uuid

