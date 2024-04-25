package com.mentor.triptrekker.booking.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "segment")
@Data
@Builder
public class SegmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "departure_id")
    private DepartureEntity departure;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "arrival_id")
    private ArrivalEntity arrival;

    private String SegmentCarrierCode;
    private String number;

    @Embedded
    private AircraftEntity aircraft;

    @Embedded
    private OperatingEntity operating;

    private String duration;
    private int numberOfStops;
    private boolean blacklistedInEU;
}
