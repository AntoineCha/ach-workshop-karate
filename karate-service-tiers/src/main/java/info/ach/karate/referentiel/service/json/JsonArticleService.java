package info.ach.karate.referentiel.service.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import info.ach.karate.referentiel.dto.ArticlesDto;
import info.ach.karate.referentiel.model.Article;
import info.ach.karate.referentiel.service.IArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class JsonArticleService implements IArticleService {

    private static ObjectMapper mapper = new ObjectMapper();

    private static Map<String, Article> articles;

    static {
        mapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
    }

    @PostConstruct
    public void init() {
        if(articles == null) {
            try {
                ArticlesDto articlesDto = mapper.readValue(getClass().getClassLoader().getResource("articles.json"),  ArticlesDto.class);
                this.articles = articlesDto.getArticles().stream().collect(Collectors.toMap(Article::getEan, a -> a));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Article> listAll() {
        return articles.values().stream().collect(Collectors.toList());
    }

    public Article get(String ean) {
        return articles.get(ean);
    }

}
