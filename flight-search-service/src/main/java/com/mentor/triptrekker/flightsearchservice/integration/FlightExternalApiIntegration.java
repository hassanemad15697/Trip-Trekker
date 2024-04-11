package com.mentor.triptrekker.flightsearchservice.integration;

import com.mentor.triptrekker.flightsearchservice.config.WebClientConfig;
import com.mentor.triptrekker.flightsearchservice.model.FlightSearchCriteria;
import com.mentor.triptrekker.flightsearchservice.model.FlightSearchData;
import com.mentor.triptrekker.flightsearchservice.request.FlightRequest;
import com.mentor.triptrekker.flightsearchservice.response.FlightResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class FlightExternalApiIntegration {

	private final WebClientConfig webClientConfig;

	public Mono<FlightResponse> searchFlights(FlightRequest request) {
		return this.webClientConfig.webClient().get()
				.uri(uriBuilder -> uriBuilder.path("/search")
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
