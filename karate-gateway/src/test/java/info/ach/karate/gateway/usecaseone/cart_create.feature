Feature: use case one - create a cart

  Scenario: creates a cart
    Given url BASE_URL
      And header Content-Type = 'application/json'
      And request {}
    When method post
    Then status 200
      And match response ==
      """{"result":{"uuid":"#notnull","article":[],"customer":null},"code":null,"detail":null}"""

  Scenario: creates a cart with article
    # create cart
    Given url BASE_URL
    And header Content-Type = 'application/json'
    And request {}
    When method post
    Then status 200
    And match response ==
      """{"result":{"uuid":"#notnull","article":[],"customer":null},"code":null,"detail":null}"""
    * def uuidCart = response.result.uuid

    # add article
    Given url BASE_URL + '/' + uuidCart + '/article'
    And request '9781491950358'
    When method post
    Then status 200
    And match response ==
      """{"result":{"uuid":"#notnull","article":[{"ean":"9781491950358","label":"Building Microservices","description":"Distributed systems have become more fine-grained in the past 10 years, shifting from code-heavy monolithic applications to smaller, self-contained microservices"}],"customer":null},"code":null,"detail":null}"""
