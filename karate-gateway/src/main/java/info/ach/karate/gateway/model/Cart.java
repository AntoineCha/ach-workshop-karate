package info.ach.karate.gateway.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Cart {

    private String uuid = UUID.randomUUID().toString();

    private List<Article> article = new ArrayList<>();

    private Customer customer;

    public String getUuid() {
        return uuid;
    }

    public List<Article> getArticle() {
        return article;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
