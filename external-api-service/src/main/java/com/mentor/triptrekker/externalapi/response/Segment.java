package com.mentor.triptrekker.externalapi.response;

import lombok.Data;

import jakarta.validation.constraints.NotNull;
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
