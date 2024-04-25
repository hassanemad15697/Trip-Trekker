package com.mentor.triptrekker.booking.model;

import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Data;

@Embeddable
@Data
@Builder
public class LocationEntity {
    private String cityCode;
    private String countryCode;
}
