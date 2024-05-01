package com.mentor.triptrekker.booking.controller;

import com.mentor.triptrekker.booking.request.FlightBookingRequest;
import com.mentor.triptrekker.booking.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/create")
    public ResponseEntity<?> createBooking(@RequestBody FlightBookingRequest bookingData, @RequestParam("userId") Integer userId){
        return new ResponseEntity<>(bookingService.processBooking(bookingData, userId));
    }

    @GetMapping("/confirm")
    public ResponseEntity<?> confirmBooking(@RequestParam("bookingId") Long bookingId){
        bookingService.successBookingStatus(bookingId);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }
    @GetMapping("/cancel")
    public ResponseEntity<?> cancelBooking(@RequestParam("bookingId") Long bookingId){
        bookingService.cancelBookingStatus(bookingId);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }
    @GetMapping("/error")
    public ResponseEntity<?> confirmBoking(@RequestParam("bookingId") Long bookingId){
        bookingService.failedBookingStatus(bookingId);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }
}
