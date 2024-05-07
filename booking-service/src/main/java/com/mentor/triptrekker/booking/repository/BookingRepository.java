package com.mentor.triptrekker.booking.repository;

import com.mentor.triptrekker.booking.model.FlightBooking;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<FlightBooking, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select r from FlightBooking r where r.id = :id")
    Optional<FlightBooking> findByIdWithPessimisticLock(Long id);
}
