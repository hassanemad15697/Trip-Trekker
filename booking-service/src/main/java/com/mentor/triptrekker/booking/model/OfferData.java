package com.mentor.triptrekker.booking.model;

import jakarta.persistence.*;

@Entity
@Table(name = "flight_offer")
class OfferData {
    private String type;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String source;
    private boolean instantTicketingRequired;
    private boolean nonHomogeneous;
    private boolean oneWay;
    private String lastTicketingDate;
    private int numberOfBookableSeats;
//    @OneToMany(cascade = CascadeType.ALL)
//    private List<ItineraryEntity> itineraries;
//    private Price price;
//    private PricingOptions pricingOptions;
//    private List<String> validatingAirlineCodes;
//    private List<TravelerPricing> travelerPricings;
}
