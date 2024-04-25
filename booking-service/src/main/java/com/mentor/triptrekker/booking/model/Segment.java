package com.mentor.triptrekker.booking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Segment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "departure_id", referencedColumnName = "id")
    private Departure departure;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "arrival_id", referencedColumnName = "id")
    private Arrival arrival;

    private String carrierCode;

    private String number;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "aircraft_id", referencedColumnName = "id")
    private Aircraft aircraft;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "operating_id", referencedColumnName = "id")
    private Operating operating;

    private String duration;


    private int numberOfStops;

    private boolean blacklistedInEU;
    @ManyToOne
    @JoinColumn(name = "itinerary_id")
    private Itinerary itinerary;
}
