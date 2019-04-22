package info.ach.karate.gateway.mock;

import com.github.tomakehurst.wiremock.http.ContentTypeHeader;
import wiremock.org.apache.http.entity.ContentType;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class ArticleMockUtils {

    public static void init() {
        stubFor(get(urlMatching("/referentiel/article/9780321125215"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader(ContentTypeHeader.KEY, ContentType.APPLICATION_JSON.toString())
                        .withBodyFile("referentiel/articles/9780321125215.json")));

        stubFor(get(urlMatching("/referentiel/article/9781491950358"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader(ContentTypeHeader.KEY, ContentType.APPLICATION_JSON.toString())
                        .withBodyFile("referentiel/articles/9781491950358.json")));

    }

    public static void mockTimeoutBehaviour_whenCallEan9781491950358() {
        stubFor(get(urlMatching("/referentiel/article/9781491950358"))
                .willReturn(aResponse()
                        .withFixedDelay(3000)
                        .withStatus(200)
                        .withHeader(ContentTypeHeader.KEY, ContentType.APPLICATION_JSON.toString())
                        .withBodyFile("referentiel/articles/9781491950358.json")));
    }

    public static void mockNoFoundBehaviour_whenCallEan9781491950358() {
        stubFor(get(urlMatching("/referentiel/article/9781491950358"))
                .willReturn(aResponse()
                        .withStatus(404)
                        .withHeader(ContentTypeHeader.KEY, ContentType.APPLICATION_JSON.toString())));

    }
}
