package flightsearchservice.integration;

import flightsearchservice.model.FlightSearchCriteria;
import flightsearchservice.model.FlightSearchData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Slf4j
public class FlightExternalApiIntegration {

	private final RestTemplate restTemplate;

	public FlightExternalApiIntegration(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	public FlightSearchData searchFlights(FlightSearchCriteria criteria) {
		// should be async with completeable Future in case of multiple API Calls in the
		// Flight service

		log.info("criteria : {} " , criteria.toString());
		// Set query parameters using UriComponentsBuilder
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(rapidApiUrl)
				.queryParam("to", criteria.getTo()).queryParam("from", criteria.getFrom())
				.queryParam("depart-date", criteria.getDepartDate())
				.queryParam("return-date", criteria.getReturnDate());

		// Build URL with query parameters
		String fullUrl = builder.toUriString();

		log.info("fullUrl : {}" , fullUrl);
		
		// Set headers
		HttpHeaders headers = new HttpHeaders();
		headers.set("X-RapidAPI-Key", rapidApiKey);
		headers.set("X-RapidAPI-Host", rapidApiHost);
		HttpEntity<String> entity = new HttpEntity<>(headers);

		// Make API call
		ResponseEntity<FlightSearchData> response = restTemplate.exchange(fullUrl, HttpMethod.GET, entity,
				FlightSearchData.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		} else {
			log.info("API Call status code: {}" , response.getStatusCode());
			throw new RuntimeException("Failed to retrieve flight data. Status code: " + response.getStatusCodeValue());
		}
	}
	
//	public FlightSearchData searchFlightsGenerator(FlightSearchCriteria criteria) {
//		log.info("criteria : {} " , criteria.toString());
//		return FlightDataResponseGenerator.getFlightDataResponse();
//	}

}
