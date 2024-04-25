package com.mentor.triptrekker.booking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Dictionaries {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "dictionary", cascade = CascadeType.ALL, orphanRemoval = true)
    @MapKeyColumn(name = "location_key")
    private Map<String, Location> locations;

    @ElementCollection
    @CollectionTable(name = "dictionary_aircraft", joinColumns = @JoinColumn(name = "dictionaries_id"))
    @MapKeyColumn(name = "aircraft_key")
    @Column(name = "aircraft_name")
    private Map<String, String> aircraft;

    @ElementCollection
    @CollectionTable(name = "dictionary_currencies", joinColumns = @JoinColumn(name = "dictionaries_id"))
    @MapKeyColumn(name = "currency_key")
    @Column(name = "currency_name")
    private Map<String, String> currencies;

    @ElementCollection
    @CollectionTable(name = "dictionary_carriers", joinColumns = @JoinColumn(name = "dictionaries_id"))
    @MapKeyColumn(name = "carrier_key")
    @Column(name = "carrier_name")
    private Map<String, String> carriers;
}