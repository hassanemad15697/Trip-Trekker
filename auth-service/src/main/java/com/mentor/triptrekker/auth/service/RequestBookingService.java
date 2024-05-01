package com.mentor.triptrekker.auth.service;


import com.mentor.triptrekker.auth.auditingAware.ApplicationAuditAware;
import com.mentor.triptrekker.auth.model.ValidationType;
import com.mentor.triptrekker.auth.request.booking.FlightBookingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class RequestBookingService {

    private final RestTemplate restTemplate;
    private final ApplicationAuditAware applicationAuditAware;

    public ResponseEntity<?> forwardToBookingService(FlightBookingRequest requestToValidate, ValidationType validationType){
        System.out.println("user id who book : "+ applicationAuditAware.getCurrentAuditor().get());

        HttpStatusCode bookingStatusCode = callBookingService(requestToValidate, applicationAuditAware.getCurrentAuditor().get());

        return new ResponseEntity<>(bookingStatusCode);

    }
    private HttpStatusCode callBookingService(FlightBookingRequest flightBookingRequest, Integer userId) {
        return restTemplate.postForEntity(
                "http://localhost:8082/booking/create?userId="+userId,
                flightBookingRequest,
                HttpStatusCode.class
        ).getStatusCode();
    }
}
