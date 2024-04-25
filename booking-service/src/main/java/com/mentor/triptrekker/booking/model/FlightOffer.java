package com.mentor.triptrekker.booking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightOfferId;

    private String type;

    private String id;

    private String source;

    private boolean instantTicketingRequired;
    private boolean nonHomogeneous;
    private boolean oneWay;

    private String lastTicketingDate;

    private int numberOfBookableSeats;

    @OneToMany(mappedBy = "flightOffer", cascade = CascadeType.ALL)
    private List<Itinerary> itineraries;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "price_id")
    private Price price;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pricing_options_id")
    private PricingOptions pricingOptions;

    @ElementCollection
    @CollectionTable(name = "validating_airline_codes", joinColumns = @JoinColumn(name = "flight_offer_id"))
    @Column(name = "airline_code")
    private List<String> validatingAirlineCodes;

    @OneToMany(mappedBy = "flightOffer", cascade = CascadeType.ALL)
    private List<TravelerPricing> travelerPricings;
}