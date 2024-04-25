package com.mentor.triptrekker.booking.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class TravelerPricing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String travelerId;

    private String fareOption;

    private String travelerType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "price_id", referencedColumnName = "id")
    private Price price;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "traveler_pricing_id")
    private List<FareDetailBySegment> fareDetailsBySegment;

    @ManyToOne
    @JoinColumn(name = "flight_offer_id")
    private FlightOffer flightOffer;
}
