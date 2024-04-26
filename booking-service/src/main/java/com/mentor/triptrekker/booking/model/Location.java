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
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cityCode;

    private String countryCode;

    @ManyToOne(fetch = FetchType.LAZY)
    private Dictionaries dictionary;
}
