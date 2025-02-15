package com.mentor.triptrekker.booking.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Arrival {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String iataCode;

    private String terminal;

    private String at;

    @OneToOne(mappedBy = "arrival", cascade = CascadeType.ALL)
    private Segment segment;
}
