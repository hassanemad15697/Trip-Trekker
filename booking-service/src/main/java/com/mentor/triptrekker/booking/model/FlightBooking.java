package com.mentor.triptrekker.booking.model;

import com.mentor.triptrekker.booking.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Entity
@Table
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class FlightBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "flight_offer_id")
    private FlightOffer flightOffer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dictionaries_id")
    private Dictionaries dictionaries;

    @OneToMany(mappedBy = "flightBooking", cascade = CascadeType.ALL)
    private List<TravelerData> travelersData;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_data_id")
    private PaymentData paymentData;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;
}