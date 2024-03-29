package com.mentor.triptrekker.externalapi.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ExternalApiService {

    private final WebClient webClient;


    public Mono<String> fetchData() {
        return this.webClient.get()
                .uri("/data-endpoint") // Replace with the actual endpoint
                .retrieve()
                .bodyToMono(String.class);
        // You can also handle errors and transform the response if needed
    }
}