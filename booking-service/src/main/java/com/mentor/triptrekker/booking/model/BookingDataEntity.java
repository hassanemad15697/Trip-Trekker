package com.mentor.triptrekker.booking.model;

import com.mentor.triptrekker.booking.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

import java.util.List;

@Entity
@Table(name = "booking_data")
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookingDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne(cascade = CascadeType.ALL)
    private OfferData data;
    @OneToOne(cascade = CascadeType.ALL)
    private Dictionaries dictionaries;
    @OneToMany(cascade = CascadeType.ALL)
    @ElementCollection
    private List<TravelerData> travelersData;
    private BookingStatus status;
    private Integer paymentId;
}



