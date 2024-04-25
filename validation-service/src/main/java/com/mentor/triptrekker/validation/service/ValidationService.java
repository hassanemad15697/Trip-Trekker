package com.mentor.triptrekker.validation.service;

import com.mentor.triptrekker.validation.config.ValidationRabbitMQConfig;
import com.mentor.triptrekker.validation.request.FlightBookingData;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidationService {

    private final RabbitTemplate validationRabbitTemplate;

    public void sendBookingRequestToBookingQueue(FlightBookingData bookingData) {
        validationRabbitTemplate.convertAndSend(ValidationRabbitMQConfig.EXCHANGE, ValidationRabbitMQConfig.KEY, bookingData);
    }
}
