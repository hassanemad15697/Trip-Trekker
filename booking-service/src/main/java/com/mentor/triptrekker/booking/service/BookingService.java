package com.mentor.triptrekker.booking.service;

import com.mentor.triptrekker.booking.enums.BookingStatus;
import com.mentor.triptrekker.booking.model.FlightBooking;
import com.mentor.triptrekker.booking.repository.BookingRepository;
import com.mentor.triptrekker.booking.request.FlightBookingData;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class BookingService {
    private final BookingRepository bookingRepository;

    private final RabbitTemplate bookingRabbitTemplate;

    private final ModelMapper modelMapper;

    @RabbitListener(queues = "validationQueue")
    public void receiveMessage(FlightBookingData bookingData) {
        // Process booking data
        processBooking(bookingData);
    }

    private void processBooking(FlightBookingData bookingData) {
        System.out.println("booking data received: " + bookingData.getTravelersData().get(0).getFirstName());
        // Booking logic here
        FlightBooking bookingDataEntity = modelMapper.map(bookingData, FlightBooking.class);
        bookingDataEntity.setStatus(BookingStatus.UNCONFIRMED);
        FlightBooking saveUnconfirmedBooking = bookingRepository.save(bookingDataEntity);
        System.out.println("save unconfirmed booking id  :"+saveUnconfirmedBooking.getId());
//        sendMessage(bookingDataEntity);

    }

//    @RabbitListener(queues = PaymentRabbitMQConfig.PAYMENT_QUEUE)
//    public void sendMessage(BookingDataEntity bookingData) {
//        rabbitMQSender.sendMessage(PaymentRabbitMQConfig.PAYMENT_EXCHANGE, "payment", bookingData);
//    }

}
