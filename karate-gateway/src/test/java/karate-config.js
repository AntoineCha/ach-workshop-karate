function() {
    // ma config ici
    var config = {
        BASE_URL : "http://localhost:18080/api/cart",

        createCart: 'classpath:info/ach/karate/gateway/commons/cart_create.feature',
        addArticle: 'classpath:info/ach/karate/gateway/commons/cart_add_article.feature'
    }
    return config;
}
