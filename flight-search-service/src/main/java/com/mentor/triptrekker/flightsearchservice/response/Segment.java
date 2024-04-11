package com.mentor.triptrekker.flightsearchservice.response;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class Segment {
    @NotNull
    private String departureAirportCode;

    @NotNull
    private String arrivalAirportCode;

    @NotNull
    private Date departureDateTime;

    @NotNull
    private Date arrivalDateTime;
}
