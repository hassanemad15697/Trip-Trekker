package com.mentor.triptrekker.flightsearchservice.service;

import com.mentor.triptrekker.flightsearchservice.integration.FlightExternalApiIntegration;
import com.mentor.triptrekker.flightsearchservice.request.FlightRequest;
import com.mentor.triptrekker.flightsearchservice.response.FlightOfferResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class FlightSearchService {
    private final FlightExternalApiIntegration flightExternalApiIntegration;


    @Cacheable(value = "flightSearchData", key = "#guestUserId + '-' + #searchCriteria.hashCode()")
    public Mono<FlightOfferResponse> searchFlightsForGuestUser(FlightRequest request) {
        return  flightExternalApiIntegration.searchFlights(request);
    }

    @Cacheable(value = "flightSearchCriteria", key = "#loggedInUser + '-' + #searchCriteria.hashCode()")
    public Mono<FlightOfferResponse> searchFlightsForLoggedInUser(String loggedInUser, FlightRequest request) {
        return flightExternalApiIntegration.searchFlights(request);


    }

}
