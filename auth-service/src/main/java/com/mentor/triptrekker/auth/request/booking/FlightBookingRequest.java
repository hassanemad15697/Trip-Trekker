package com.mentor.triptrekker.auth.request.booking;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightBookingRequest {
    @NotNull
    @Valid
    private FlightOffer data;

    @NotNull
    @Valid
    private Dictionaries dictionaries;

    @NotEmpty
    @Valid
    private List<TravelerData> travelersData;

    @NotNull
    @Valid
    private PaymentMethod paymentMethod;

//    @Data
//    @NoArgsConstructor
//    @AllArgsConstructor
//    @Builder
//    public static class PaymentData {
//        @NotBlank
//        @Pattern(regexp = "^[0-9]{16}$", message = "Invalid card number format")
//        private String cardNumber;
//
//        @NotBlank
//        @Pattern(regexp = "^(0[1-9]|1[0-2])/[0-9]{2}$", message = "Invalid expiration date format")
//        private String expireDate;
//
//        @NotBlank
//        @Size(min = 3, max = 3, message = "CVC must be 3 digits")
//        private String CVC;
//    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class TravelerData {
        @NotBlank
        private String firstName;

        @NotBlank
        private String lastName;

        @Email
        private String email;

        @NotBlank
        private String phoneNumber;

        @NotBlank
        @Pattern(regexp = "MALE|FEMALE")
        private String gender;

        @NotBlank
        @Pattern(regexp = "ADULT|CHILD|INFANTS")
        private String type;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class FlightOffer {
        @NotBlank
        private String type;

        @NotBlank
        private String id;

        @NotBlank
        private String source;

        private boolean instantTicketingRequired;
        private boolean nonHomogeneous;
        private boolean oneWay;

        @NotBlank
        private String lastTicketingDate;

        @Min(1)
        private int numberOfBookableSeats;

        @NotEmpty
        @Valid
        private List<Itinerary> itineraries;

        @NotNull
        @Valid
        private Price price;

        @NotNull
        @Valid
        private PricingOptions pricingOptions;

        @NotEmpty
        private List<String> validatingAirlineCodes;

        @NotEmpty
        @Valid
        private List<TravelerPricing> travelerPricings;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Itinerary {
        @NotBlank
        private String duration;

        @NotEmpty
        @Valid
        private List<Segment> segments;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Segment {
        @NotNull
        @Valid
        private Departure departure;

        @NotNull
        @Valid
        private Arrival arrival;

        @NotBlank
        private String carrierCode;

        @NotBlank
        private String number;

        @NotNull
        @Valid
        private Aircraft aircraft;

        @NotNull
        @Valid
        private Operating operating;

        @NotBlank
        private String duration;

        @Min(0)
        private int numberOfStops;

        private boolean blacklistedInEU;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Departure {
        @NotBlank
        private String iataCode;

        private String terminal;

        @NotBlank
        private String at;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Arrival {
        @NotBlank
        private String iataCode;

        private String terminal;

        @NotBlank
        private String at;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Aircraft {
        @NotBlank
        private String code;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Operating {
        @NotBlank
        private String carrierCode;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Price {
        @NotBlank
        private String currency;

        @NotBlank
        private String total;

        @NotBlank
        private String base;

        @NotEmpty
        @Valid
        private List<Fee> fees;

        @NotBlank
        private String grandTotal;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Fee {
        @NotBlank
        private String amount;

        @NotBlank
        private String type;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PricingOptions {
        @NotEmpty
        private List<String> fareType;

        private boolean includedCheckedBagsOnly;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class TravelerPricing {
        @NotBlank
        private String travelerId;

        @NotBlank
        private String fareOption;

        @NotBlank
        private String travelerType;

        @NotNull
        @Valid
        private Price price;

        @NotEmpty
        @Valid
        private List<FareDetailBySegment> fareDetailsBySegment;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class FareDetailBySegment {
        @NotBlank
        private String segmentId;

        @NotBlank
        private String cabin;

        @NotBlank
        private String fareBasis;

        @NotBlank
        private String clazz;

        @NotNull
        @Valid
        private IncludedCheckedBags includedCheckedBags;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class IncludedCheckedBags {
        @Min(0)
        private int weight;

        @NotBlank
        private String weightUnit;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Dictionaries {
        @NotNull
        private Map<String, Location> locations;

        @NotNull
        private Map<String, String> aircraft;

        @NotNull
        private Map<String, String> currencies;

        @NotNull
        private Map<String, String> carriers;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Location {
        @NotBlank
        private String cityCode;

        @NotBlank
        private String countryCode;
    }
}
