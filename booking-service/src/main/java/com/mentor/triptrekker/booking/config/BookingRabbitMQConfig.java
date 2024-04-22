package com.mentor.triptrekker.booking.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookingRabbitMQConfig {

    public static final String QUEUE = "bookingQueue";
    public static final String EXCHANGE = "bookingExchange";

    @Bean
    Queue bookingQueue_inBooking() {
        return new Queue(QUEUE, false);
    }

    @Bean
    DirectExchange bookingExchange_inBooking() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    Binding bookingBinding_inBooking(@Qualifier("bookingQueue_inBooking")Queue bookingQueue_inBooking, @Qualifier("bookingExchange_inBooking") DirectExchange bookingExchange_inBooking) {
        return BindingBuilder.bind(bookingQueue_inBooking).to(bookingExchange_inBooking).with("booking");
    }
}
