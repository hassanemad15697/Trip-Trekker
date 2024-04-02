package com.mentor.triptrekker.externalapi.response;

import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.util.List;

@Data
public class Itinerary {
    @NotNull
    private String duration;

    @NotNull
    private List<Segment> segments;

}

