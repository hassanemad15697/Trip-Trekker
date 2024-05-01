package com.mentor.triptrekker.booking.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class BookingRabbitMQConfig {

    @Value("${payment.timeout}")
    private Long paymentTimeout;

    //VALIDATION
    public static final String VALIDATION_QUEUE = "validationQueue";
    // Queue, Exchange, Routing Key Declarations
    public static final String BOOKING_QUEUE = "bookingQueue";
    public static final String BOOKING_EXCHANGE = "bookingExchange";
    public static final String BOOKING_KEY = "bookingKey";

    public static final String PAYMENT_DELAY_QUEUE = "paymentDelayQueue";
    public static final String PAYMENT_DELAY_EXCHANGE = "paymentDelayExchange";
    public static final String PAYMENT_DELAY_KEY = "paymentDelayKey";


    @Bean
    Queue bookingQueue() {
        return new Queue(BOOKING_QUEUE, true, false, false);
    }

    @Bean
    DirectExchange bookingExchange() {
        return new DirectExchange(BOOKING_EXCHANGE);
    }

    @Bean
    Binding bookingBinding(@Qualifier("bookingQueue")Queue bookingQueue, @Qualifier("bookingExchange") DirectExchange bookingExchange) {
        return BindingBuilder.bind(bookingQueue).to(bookingExchange).with(BOOKING_KEY);
    }

    // Delay and Payment Check Queue and Exchange Configuration
    @Bean
    Queue paymentDelayQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange", PAYMENT_DELAY_EXCHANGE);
        args.put("x-dead-letter-routing-key", PAYMENT_DELAY_KEY);
        args.put("x-message-ttl", paymentTimeout); // Set TTL as configured in properties
        return new Queue(PAYMENT_DELAY_QUEUE, false, false, false, args);
    }

    @Bean
    DirectExchange paymentDelayExchange() {
        return new DirectExchange(PAYMENT_DELAY_EXCHANGE);
    }

    @Bean
    Binding paymentCheckBinding(@Qualifier("paymentDelayQueue")Queue paymentDelayQueue, @Qualifier("paymentDelayExchange") DirectExchange paymentDelayExchange) {
        return BindingBuilder.bind(paymentDelayQueue).to(paymentDelayExchange).with(PAYMENT_DELAY_KEY);
    }

    // RabbitTemplate Configuration
    @Bean
    public RabbitTemplate bookingRabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(bookingMessageConverter());
        rabbitTemplate.setConfirmCallback((CorrelationData correlationData, boolean ack, String cause) -> {
            if (!ack) {
                System.out.println("Message with CorrelationData " + correlationData + " failed with cause " + cause);
            }
        });
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter bookingMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
