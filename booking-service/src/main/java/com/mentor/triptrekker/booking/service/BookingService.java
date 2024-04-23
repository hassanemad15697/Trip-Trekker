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
        // process the booking with unconfirmed status


        // publish the payment data and order it to the payment service to complete the payment
        System.out.println("booking data received with name : "+bookingData.getTravelersData().get(0).getFirstName());
        System.out.println("booking data received with card number : "+bookingData.getPaymentData().getCardNumber());
        // Booking logic here
    }

    private void confirmBooking(Integer bookingId){
        // update the booking status to be confirmed
    }


    private void notify(String message){

        // publish message to notification service
    }



}
