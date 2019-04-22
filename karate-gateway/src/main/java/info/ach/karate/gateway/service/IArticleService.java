package info.ach.karate.gateway.service;

import info.ach.karate.gateway.exception.NotFoundException;
import info.ach.karate.gateway.model.Article;

public interface IArticleService {

    Article getArticle(String ean) throws Exception;
}
