package com.mentor.triptrekker.flightsearchservice.controller;


import com.mentor.triptrekker.flightsearchservice.request.FlightRequest;
import com.mentor.triptrekker.flightsearchservice.response.FlightOfferResponse;
import com.mentor.triptrekker.flightsearchservice.service.FlightSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("v1/flight")
@RequiredArgsConstructor
public class FlightController {

    private final FlightSearchService flightSearchService;


    @PostMapping("/search")
    public Mono<ResponseEntity<FlightOfferResponse>> searchFlights(@RequestBody FlightRequest request) {
        return flightSearchService.searchFlightsForGuestUser(request)
                .map(response -> ResponseEntity.ok().body(response))
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    }


}
