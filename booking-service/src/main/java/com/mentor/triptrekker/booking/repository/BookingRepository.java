package com.mentor.triptrekker.booking.repository;

import com.mentor.triptrekker.booking.model.FlightBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<FlightBooking, Long> {

}
