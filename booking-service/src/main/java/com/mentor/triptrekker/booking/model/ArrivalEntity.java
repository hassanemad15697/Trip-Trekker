package com.mentor.triptrekker.booking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "arrival")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArrivalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String iataCode;
    private String terminal;
    private String at;
}
