package com.mentor.triptrekker.booking.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "itinerary")
@Data
public class ItineraryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String duration;

    @OneToMany(cascade = CascadeType.ALL)
    private List<SegmentEntity> segments;
}
