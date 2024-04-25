package com.mentor.triptrekker.booking.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookingRabbitMQConfig {

    public static final String QUEUE = "bookingQueue";
    public static final String EXCHANGE = "bookingExchange";

    @Bean
    Queue bookingQueue() {
        return new Queue(QUEUE, false);
    }

    @Bean
    DirectExchange bookingExchange() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    Binding bookingBinding(@Qualifier("bookingQueue")Queue bookingQueue, @Qualifier("bookingExchange") DirectExchange bookingExchange) {
        return BindingBuilder.bind(bookingQueue).to(bookingExchange).with("bookingKey");
    }

    @Bean
    public RabbitTemplate bookingRabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(bookingMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter bookingMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
