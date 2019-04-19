package info.ach.karate.gateway.service.impl;

import info.ach.karate.gateway.dto.JsonResponseDto;
import info.ach.karate.gateway.model.Customer;
import info.ach.karate.gateway.service.ICustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DefaultCustomerService implements ICustomerService {
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
    public Customer getCustomer(String uuid) {
        StringBuilder urlBuilder = new StringBuilder();
        urlBuilder.append(apiReferentielUrl);
        urlBuilder.append("/referentiel/customer/");
        urlBuilder.append(uuid);

        try {
            JsonResponseDto<Customer> wrapper = restTemplate.exchange(urlBuilder.toString(), HttpMethod.GET, null, new ParameterizedTypeReference<JsonResponseDto<Customer>>() {
            }).getBody();

            return wrapper.getResult();
        } catch (Exception e) {
            LOG.error("Error during retrieving uuid {}", uuid, e);
        }
        return null;
    }
}
