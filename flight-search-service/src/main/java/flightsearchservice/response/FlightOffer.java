package flightsearchservice.response;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class FlightOffer {
    @NotNull
    private String id;

    @NotNull
    private List<Itinerary> itineraries;

    @NotNull
    private Double price;

    // Add any other fields if necessary
}

