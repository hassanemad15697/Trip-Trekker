package com.mentor.triptrekker.auth.controller;

import com.mentor.triptrekker.auth.request.booking.FlightBookingRequest;
import com.mentor.triptrekker.auth.service.RequestBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.mentor.triptrekker.auth.model.ValidationType.BOOKING_FLIGHT;

@RestController
@RequestMapping("api/booking")
@RequiredArgsConstructor
public class BookingController {

    private final RequestBookingService requestBookingService;

    @PostMapping("/flight")
    public ResponseEntity<?> startBooking(@RequestBody FlightBookingRequest bookingRequest) {
        return requestBookingService.forwardToBookingService(bookingRequest,BOOKING_FLIGHT );
    }
}
