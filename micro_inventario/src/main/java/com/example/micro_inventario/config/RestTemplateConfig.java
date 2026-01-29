package com.example.micro_inventario.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

@Configuration
public class RestTemplateConfig {

    @Value("${api.key}")
    private String apiKey;

    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(3000);
        factory.setReadTimeout(5000);

        RestTemplate restTemplate = new RestTemplate(factory);
        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("X-API-KEY", apiKey);
            return execution.execute(request, body);
        });

        return restTemplate;
    }
}



