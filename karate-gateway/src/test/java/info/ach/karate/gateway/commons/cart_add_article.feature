Feature: add article in cart

  Scenario: add article in cart
    Given url BASE_URL + "/" + uuidCart + "/article"
    And request ean
    When method post
    * print response


