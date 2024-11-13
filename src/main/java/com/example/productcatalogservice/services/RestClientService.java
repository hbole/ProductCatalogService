package com.example.productcatalogservice.services;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class RestClientService {
//    private final RestTemplateBuilder restTemplateBuilder;
    private final RestTemplate restTemplate;

    public RestClientService(
            RestTemplate restTemplate
    ) {
        this.restTemplate = restTemplate;
    }

    public  <T> ResponseEntity<T> requestForEntity(String url, HttpMethod httpMethod, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
//        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }
}
