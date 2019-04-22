package info.ach.karate.gateway.service;

import info.ach.karate.gateway.model.Cart;

public interface ICartService {

    Cart create();

    Cart get(String cartUuid);

    Cart addArticle(String cartUuid, String ean) throws Exception;

    Cart addCustomer(String cartUuid, String uuid);
}
