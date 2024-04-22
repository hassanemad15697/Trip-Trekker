package com.mentor.triptrekker.booking.request;

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
public class FlightBookingData {
    private FlightOffer data;
    private Dictionaries dictionaries;
    private List<TravelerData> travelersData;
    private PaymentData paymentData;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PaymentData {
        private String cardNumber;
        private String expireDate;
        private String CVC;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class TravelerData {
        private String firstName;
        private String lastName;
        private String email;
        private String phoneNumber;
        private String gender;
        private String type;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class FlightOffer {
        private String type;
        private String id;
        private String source;
        private boolean instantTicketingRequired;
        private boolean nonHomogeneous;
        private boolean oneWay;
        private String lastTicketingDate;
        private int numberOfBookableSeats;
        private List<Itinerary> itineraries;
        private Price price;
        private PricingOptions pricingOptions;
        private List<String> validatingAirlineCodes;
        private List<TravelerPricing> travelerPricings;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Itinerary {
        private String duration;
        private List<Segment> segments;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Segment {
        private Departure departure;
        private Arrival arrival;
        private String carrierCode;
        private String number;
        private Aircraft aircraft;
        private Operating operating;
        private String duration;
        private int numberOfStops;
        private boolean blacklistedInEU;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Departure {
        private String iataCode;
        private String terminal;
        private String at;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Arrival {
        private String iataCode;
        private String terminal;
        private String at;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Aircraft {
        private String code;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Operating {
        private String carrierCode;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Price {
        private String currency;
        private String total;
        private String base;
        private List<Fee> fees;
        private String grandTotal;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Fee {
        private String amount;
        private String type;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class PricingOptions {
        private List<String> fareType;
        private boolean includedCheckedBagsOnly;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class TravelerPricing {
        private String travelerId;
        private String fareOption;
        private String travelerType;
        private Price price;
        private List<FareDetailBySegment> fareDetailsBySegment;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class FareDetailBySegment {
        private String segmentId;
        private String cabin;
        private String fareBasis;
        private String clazz;
        private IncludedCheckedBags includedCheckedBags;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class IncludedCheckedBags {
        private int weight;
        private String weightUnit;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Dictionaries {
        private Map<String, Location> locations;
        private Map<String, String> aircraft;
        private Map<String, String> currencies;
        private Map<String, String> carriers;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Location {
        private String cityCode;
        private String countryCode;
    }
}
