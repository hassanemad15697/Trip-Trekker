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
public class Itinerary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String duration;

    @OneToMany(mappedBy = "itinerary", cascade = CascadeType.ALL)
    private List<Segment> segments;

    @ManyToOne
    @JoinColumn(name = "flight_offer_id")
    private FlightOffer flightOffer;
}

