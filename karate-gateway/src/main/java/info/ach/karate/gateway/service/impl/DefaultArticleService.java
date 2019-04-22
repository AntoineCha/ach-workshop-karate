package info.ach.karate.gateway.service.impl;

import info.ach.karate.gateway.dto.JsonResponseDto;
import info.ach.karate.gateway.exception.NotFoundException;
import info.ach.karate.gateway.exception.TimeoutException;
import info.ach.karate.gateway.model.Article;
import info.ach.karate.gateway.service.IArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.net.SocketTimeoutException;

@Service
public class DefaultArticleService implements IArticleService {
    private static final Logger LOG = LoggerFactory.getLogger(DefaultArticleService.class);

    private static RestTemplate restTemplate;

    @Value("${api.referentiel.url}")
    private String apiReferentielUrl;


    static {
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setReadTimeout(1000);

        restTemplate = new RestTemplate(httpRequestFactory);
    }

    @Override
    public Article getArticle(String ean) throws Exception {

        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(apiReferentielUrl);
        urlBuilder.append("/referentiel/article/");
        urlBuilder.append(ean);

        try {
            JsonResponseDto<Article> wrapper = restTemplate.exchange(urlBuilder.toString(), HttpMethod.GET, null, new ParameterizedTypeReference<JsonResponseDto<Article>>() {
            }).getBody();

            return wrapper.getResult();
        } catch (HttpClientErrorException e) {
            if(e.getStatusCode().value() == 404) {
                throw new NotFoundException();
            }
            LOG.error("Error during retrieving ean {}", ean, e);
            throw e;
        } catch (ResourceAccessException e) {
            if(e.getCause() instanceof SocketTimeoutException) {
                throw new TimeoutException();
            }
            LOG.error("Error during retrieving ean {}", ean, e);
            throw e;
        }
    }
}
