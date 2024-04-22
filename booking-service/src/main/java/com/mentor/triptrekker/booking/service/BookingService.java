package com.mentor.triptrekker.booking.service;

import com.mentor.triptrekker.booking.config.BookingRabbitMQConfig;
import com.mentor.triptrekker.booking.request.FlightBookingData;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    @RabbitListener(queues = BookingRabbitMQConfig.QUEUE)
    public void receiveMessage(FlightBookingData bookingData) {
        // Process booking data
        processBooking(bookingData);
    }

    private void processBooking(FlightBookingData bookingData) {
        System.out.println("booking data received: "+bookingData.getTravelersData().get(0).getFirstName());
        // Booking logic here
    }
}
