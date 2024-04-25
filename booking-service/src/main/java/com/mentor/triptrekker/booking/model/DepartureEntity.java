package com.mentor.triptrekker.booking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "departure")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepartureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String iataCode;
    private String terminal;
    private String at;
}
