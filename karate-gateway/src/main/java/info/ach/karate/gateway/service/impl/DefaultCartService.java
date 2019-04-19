package info.ach.karate.gateway.service.impl;

import info.ach.karate.gateway.model.Cart;
import info.ach.karate.gateway.service.IArticleService;
import info.ach.karate.gateway.service.ICartService;
import info.ach.karate.gateway.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DefaultCartService implements ICartService {

    @Autowired
    private IArticleService articleService;

    @Autowired
    private ICustomerService customerService;

    public static Map<String, Cart> carts = new HashMap<>();

    @Override
    public Cart create() {
        Cart cart = new Cart();
        carts.put(cart.getUuid(), cart);
        return cart;
    }

    @Override
    public Cart get(String cartUuid) {
        return carts.get(cartUuid);
    }

    @Override
    public Cart addArticle(String cartUuid, String ean) {
        Cart cart = carts.get(cartUuid);
        cart.getArticle().add(articleService.getArticle(ean));
        return cart;
    }

    @Override
    public Cart addCustomer(String cartUuid, String uuid) {
        Cart cart = carts.get(cartUuid);
        cart.setCustomer(customerService.getCustomer(uuid));
        return cart;
    }
}
