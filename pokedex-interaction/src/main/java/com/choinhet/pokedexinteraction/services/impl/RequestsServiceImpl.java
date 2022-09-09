package com.choinhet.pokedexinteraction.services.impl;

import com.choinhet.pokedexinteraction.objects.Pokedex;

import com.choinhet.pokedexinteraction.services.IRequestsService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class RequestsServiceImpl implements IRequestsService {
    private final RestTemplate restTemplate;
    @Value("#{${project.urlParams}}")
    private Map<String, String> httpParams;

    public RequestsServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private @NotNull HttpEntity<String> getHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
        return new HttpEntity<>(headers);
    }

    @Bean
    public Pokedex requestPokedex() {
        String pokedexUrl = httpParams.get("baseUrl") + "?limit=" + httpParams.get("limit");
        HttpEntity<String> entity = getHttpEntity();
        return restTemplate.exchange(
                pokedexUrl,
                HttpMethod.GET,
                entity,
                Pokedex.class).getBody();
    }

}
