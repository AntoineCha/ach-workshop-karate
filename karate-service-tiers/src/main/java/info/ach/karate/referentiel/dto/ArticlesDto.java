package info.ach.karate.referentiel.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import info.ach.karate.referentiel.model.Article;

import java.util.List;

@JsonAutoDetect
public class ArticlesDto {

    @JsonProperty("articles")
    private List<Article> articles;

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
