Feature: use case one - create a cart

  Background:
    * def dataset = read('../dataset.json')
    * def mockTimeoutBehaviour_whenCallEan9781491950358 =
    """
    function() {
      var ArticleMockUtils = Java.type('info.ach.karate.gateway.mock.ArticleMockUtils');
      return ArticleMockUtils.mockTimeoutBehaviour_whenCallEan9781491950358();
    }
    """
    * def mockNoFoundBehaviour_whenCallEan9781491950358 =
    """
    function() {
      var ArticleMockUtils = Java.type('info.ach.karate.gateway.mock.ArticleMockUtils');
      return ArticleMockUtils.mockNoFoundBehaviour_whenCallEan9781491950358();
    }
    """
    * def initArticleReferentiel =
    """
    function() {
      var ArticleMockUtils = Java.type('info.ach.karate.gateway.mock.ArticleMockUtils');
      return ArticleMockUtils.init();
    }
    """
    * def callInitArticleReferentiel = call initArticleReferentiel
    # create cart
    * def createCart = call read(createCart) {}
    * def uuidCart = createCart.id

  Scenario: creates a cart with article - timeout on article service

    # add article
    * call mockTimeoutBehaviour_whenCallEan9781491950358
    When def addArticle = call read(addArticle) { uuidCart: '#(uuidCart)', ean: '#(dataset.ean_building_microservices)' }
    Then match addArticle.responseStatus == 424
    And match addArticle.response == ''


  Scenario: creates a cart with article - notfound on article service

    # add article
    * call mockNoFoundBehaviour_whenCallEan9781491950358
    When def addArticle = call read(addArticle) { uuidCart: '#(uuidCart)', ean: '#(dataset.ean_building_microservices)' }
    Then match addArticle.responseStatus == 404
    And match addArticle.response == ''

