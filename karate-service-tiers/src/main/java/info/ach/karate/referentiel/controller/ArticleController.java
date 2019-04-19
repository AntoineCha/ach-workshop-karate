package info.ach.karate.referentiel.controller;

import info.ach.karate.referentiel.dto.JsonResponseDto;
import info.ach.karate.referentiel.model.Article;
import info.ach.karate.referentiel.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/referentiel/article")
public class ArticleController {

    @Autowired
    private IArticleService articleService;

    @GetMapping
    public JsonResponseDto<List<Article>> list() {
        return new JsonResponseDto(articleService.listAll());
    }

    @GetMapping("/{ean}")
    public JsonResponseDto<Article> get(@PathVariable String ean) {
        return new JsonResponseDto(articleService.get(ean));
    }


}
