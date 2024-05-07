package com.mentor.triptrekker.externalapi.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ExternalWebClientConfig {
    @Bean
    public WebClient externalWebClient() {
        return WebClient.builder()
                .baseUrl("https://test.api.amadeus.com/")
                .build();
    }
}
