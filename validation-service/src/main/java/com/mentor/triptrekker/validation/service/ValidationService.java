package com.mentor.triptrekker.validation.service;

import com.mentor.triptrekker.validation.request.FlightBookingData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ValidationService {

//    private final RabbitTemplate validationRabbitTemplate;
    private final RestTemplate restTemplate;
    public boolean sendBookingRequestToBookingQueue(FlightBookingData bookingData) {
//        validationRabbitTemplate.convertAndSend(VALIDATION_EXCHANGE, VALIDATION_KEY, bookingData);
        return callBookingService(bookingData);
    }

    private boolean callBookingService(FlightBookingData bookingData) {
        ResponseEntity<?> response = restTemplate.postForEntity(
//                "http://validation-service:8088/" + validationType.getValue(),
                "http://localhost:8088/booking/create" ,
                bookingData,
                Boolean.class
        );

        return response.getStatusCode().is2xxSuccessful();
    }


}
