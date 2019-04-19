Feature: use case one - create a cart

  Background:
    * def dataset = read('../dataset.json')

  Scenario: creates a cart
    When def createCart = call read(createCart) {}
    Then match createCart.responseStatus == 200
      And match createCart.response ==
      """{"result":{"uuid":"#notnull","article":[],"customer":null},"code":null,"detail":null}"""

  Scenario: creates a cart with article
    # create cart
    When def createCart = call read(createCart) {}
    Then match createCart.responseStatus == 200
    And match createCart.response ==
      """{"result":{"uuid":"#notnull","article":[],"customer":null},"code":null,"detail":null}"""
    # createCart.response.result.uuid
    * def uuidCart = createCart.id

    # add article
    When def addArticle = call read(addArticle) { uuidCart: '#(uuidCart)', ean: '#(dataset.ean_building_microservices)' }
    Then match addArticle.responseStatus == 200
    And match addArticle.response ==
      """{"result":{"uuid":"#notnull","article":[{"ean":"9781491950358","label":"Building Microservices","description":"Distributed systems have become more fine-grained in the past 10 years, shifting from code-heavy monolithic applications to smaller, self-contained microservices"}],"customer":null},"code":null,"detail":null}"""

