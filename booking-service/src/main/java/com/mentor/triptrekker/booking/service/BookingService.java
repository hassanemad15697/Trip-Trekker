package com.mentor.triptrekker.booking.service;

import com.mentor.triptrekker.booking.enums.BookingStatus;
import com.mentor.triptrekker.booking.model.FlightBooking;
import com.mentor.triptrekker.booking.repository.BookingRepository;
import com.mentor.triptrekker.booking.request.FlightBookingRequest;
import com.mentor.triptrekker.booking.request.PaymentRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingService {
    private final BookingRepository bookingRepository;

    private final RestTemplate restTemplate;

    private final ModelMapper modelMapper;

    @Transactional
    public HttpStatusCode processBooking(FlightBookingRequest bookingData, Integer userId) {
        // check if any available seats
        // if yes.
        // Map the received data to entity and save it
        FlightBooking booking = modelMapper.map(bookingData, FlightBooking.class);
        booking.setStatus(BookingStatus.UNCONFIRMED);
        booking.setUserId(userId);
        FlightBooking savedBooking = bookingRepository.save(booking);
        log.info("Saved unconfirmed booking ID: {}" ,savedBooking.getId());
        // decrease seated by  number of travelers --> seats-= travelersNum;
        HttpStatusCode paymentStatusCode = callPaymentService(PaymentRequest.builder()
                .bookingId(savedBooking.getId())
                .method("PayPal")
                .total(120.1)
                .currency("EUR")
                .description("booking description")
                .build());
        System.out.println("payment status code :"+paymentStatusCode.value());
        if(paymentStatusCode.is2xxSuccessful()){
            successBookingStatus(savedBooking.getId());
        }else if(paymentStatusCode.is3xxRedirection()){
            System.out.println("Redirection");
        }else {
            failedBookingStatus(savedBooking.getId());
        }

        //
        // publish on the notification queue
        return paymentStatusCode;

    }

    private HttpStatusCode callPaymentService(PaymentRequest paymentRequest) {
        return restTemplate.postForEntity(
                "http://localhost:8087/payment/create",
                paymentRequest,
                HttpStatusCode.class
        ).getStatusCode();
    }

    public void successBookingStatus(Long bookingId) {
        // Update booking status logic
        System.out.println("Booking ID " + bookingId + " status updated to " + BookingStatus.CONFIRMED.name());
    }
    public void cancelBookingStatus(Long bookingId) {
        // Update booking status logic
        System.out.println("Booking ID " + bookingId + " status updated to " + BookingStatus.PAYMENT_CANCELLED.name());
    }
    public void failedBookingStatus(Long bookingId) {
        // Update booking status logic
        System.out.println("Booking ID " + bookingId + " status updated to " + BookingStatus.PAYMENT_FAILURE.name());
    }


}
