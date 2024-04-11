package com.mentor.triptrekker.flightsearchservice.response;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class Itinerary {
    @NotNull
    private String duration;

    @NotNull
    private List<Segment> segments;

}

