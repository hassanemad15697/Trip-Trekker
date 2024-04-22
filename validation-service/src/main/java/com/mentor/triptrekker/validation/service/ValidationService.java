package com.mentor.triptrekker.validation.service;

import com.mentor.triptrekker.validation.config.ValidationRabbitMQConfig;
import com.mentor.triptrekker.validation.request.FlightBookingData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public Boolean validateBooking(FlightBookingData bookingData) {
        rabbitTemplate.convertAndSend(ValidationRabbitMQConfig.EXCHANGE, "booking", bookingData);
        return true;
    }
}
