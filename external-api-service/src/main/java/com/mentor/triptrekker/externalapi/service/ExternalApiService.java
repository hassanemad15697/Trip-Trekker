package com.mentor.triptrekker.externalapi.service;


import com.mentor.triptrekker.externalapi.exception.FlightSearchException;
import com.mentor.triptrekker.externalapi.request.FlightRequest;
import com.mentor.triptrekker.externalapi.response.FlightResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExternalApiService {

    private final WebClient webClient;


    public Mono<FlightResponse> searchFlights(FlightRequest request) {
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/shopping/flight-offers")
                        .queryParam("originLocationCode", request.getOriginLocationCode())
                        .queryParam("destinationLocationCode", request.getDestinationLocationCode())
                        .queryParam("departureDate", request.getDepartureDate())
                        .queryParam("returnDate", request.getReturnDate())
                        .queryParam("adults", request.getAdults())
                        .queryParam("children", request.getChildren())
                        .queryParam("infants", request.getInfants())
                        .queryParam("travelClass", request.getTravelClass())
                        .queryParam("includedAirlineCodes", request.getIncludedAirlineCodes())
                        .queryParam("excludedAirlineCodes", request.getExcludedAirlineCodes())
                        .queryParam("nonStop", request.getNonStop())
                        .queryParam("currencyCode", request.getCurrencyCode())
                        .queryParam("maxPrice", request.getMaxPrice())
                        .queryParam("max", request.getMax())
                        .build())
                .retrieve()
//                .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
//                        response -> Mono.error(new FlightSearchException("Error fetching flight data. ")))
                .bodyToMono(FlightResponse.class)
//                .onErrorResume(FlightSearchException.class, error -> {
//                    log.error(error.getMessage()+"\n"+ Arrays.toString(error.getStackTrace()));
//                    return Mono.just(new FlightResponse());
//                })
                ;
    }
}