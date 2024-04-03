package flightsearchservice.controller;


import flightsearchservice.request.FlightRequest;
import flightsearchservice.response.FlightResponse;
import flightsearchservice.service.FlightSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("v1/flight")
@RequiredArgsConstructor
public class FlightController {

    private final FlightSearchService flightSearchService;



    @PostMapping("/search")
    public Mono<ResponseEntity<FlightResponse>> searchFlights(@RequestBody FlightRequest request) {
        return flightSearchService.searchFlights(request)
                .map(response -> ResponseEntity.ok().body(response))
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NO_CONTENT).build());
//                .onErrorResume(FlightSearchException.class, ex -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new FlightResponse())));
    }
}
