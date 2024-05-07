package com.mentor.triptrekker.externalapi.controller;

import com.mentor.triptrekker.externalapi.request.FlightPricingRequest;
import com.mentor.triptrekker.externalapi.request.FlightRequest;
import com.mentor.triptrekker.externalapi.response.FlightOfferResponse;
import com.mentor.triptrekker.externalapi.response.FlightPricingResponse;
import com.mentor.triptrekker.externalapi.service.ExternalApiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("v1/flight")
@RequiredArgsConstructor
public class FlightController {

    private final ExternalApiService externalApiService;

    @PostMapping("/search")
    public Mono<ResponseEntity<FlightOfferResponse>> searchFlights(@Valid @RequestBody FlightRequest request) {
        return externalApiService.searchFlights(request)
                .map(response -> ResponseEntity.ok().body(response))
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    }

    @PostMapping("/price")
    public Mono<ResponseEntity<String>> flightPrice(@Valid@RequestBody FlightPricingRequest request) {
        return externalApiService.FlightPrice(request)
                .map(response -> ResponseEntity.ok().body(response))
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    }


}
