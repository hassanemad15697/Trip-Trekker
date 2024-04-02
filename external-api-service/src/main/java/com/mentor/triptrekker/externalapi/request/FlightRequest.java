package com.mentor.triptrekker.externalapi.request;

import jakarta.validation.constraints.*;
import lombok.Data;
@Data
public class FlightRequest {
    @NotNull
    @Pattern(regexp = "[A-Z]{3}")
    private String originLocationCode;

    @NotNull
    @Pattern(regexp = "[A-Z]{3}")
    private String destinationLocationCode;

    @NotNull
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}")
    private String departureDate;

    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}")
    private String returnDate;

    @NotNull
    @Min(value = 1)
    @Max(value = 9)
    private Integer adults;

    @Min(value = 0)
    @Max(value = 9)
    private Integer children;

    @Min(value = 0)
    @Max(value = 9)
    private Integer infants;

    @Pattern(regexp = "ECONOMY|PREMIUM_ECONOMY|BUSINESS|FIRST")
    private String travelClass;

    @Pattern(regexp = "[0-9A-Z]{2}(,[0-9A-Z]{2})*")
    private String includedAirlineCodes;

    @Pattern(regexp = "[0-9A-Z]{2}(,[0-9A-Z]{2})*")
    private String excludedAirlineCodes;

    private Boolean nonStop;

    @Pattern(regexp = "[A-Z]{3}")
    private String currencyCode;

    @Min(value = 1)
    private Integer maxPrice;

    @Min(value = 1)
    private Integer max;
}