package com.mentor.triptrekker.externalapi.response;

import lombok.Data;

import jakarta.validation.constraints.NotNull;
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

