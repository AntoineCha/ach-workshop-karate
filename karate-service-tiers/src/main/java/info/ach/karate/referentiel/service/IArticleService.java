package info.ach.karate.referentiel.service;

import info.ach.karate.referentiel.model.Article;

import java.util.List;

public interface IArticleService {

    List<Article> listAll();

    Article get(String ean);

}
