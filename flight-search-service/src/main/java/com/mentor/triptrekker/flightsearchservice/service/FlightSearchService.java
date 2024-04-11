package flightsearchservice.service;

import flightsearchservice.config.WebClientConfig;
import flightsearchservice.request.FlightRequest;
import flightsearchservice.response.FlightResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class FlightSearchService {
    private final WebClientConfig webClientConfig;

    public void searchFlightsForGuest() {

    }

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
