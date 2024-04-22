package com.mentor.triptrekker.validation.controller;

import com.mentor.triptrekker.validation.request.FlightBookingData;
import com.mentor.triptrekker.validation.service.ValidationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/validation/booking")
@RequiredArgsConstructor
public class ValidationServiceController {

    private final ValidationService validationService;
    @PostMapping("/flight")
    public ResponseEntity<?> validateBookingData(@Valid @RequestBody FlightBookingData bookingData) {
        validationService.validateBooking(bookingData);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
