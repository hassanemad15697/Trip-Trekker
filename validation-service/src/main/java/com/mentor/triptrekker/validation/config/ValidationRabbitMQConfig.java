package com.mentor.triptrekker.validation.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

@Configuration
public class ValidationRabbitMQConfig {

    public static final String QUEUE = "bookingQueue";
    public static final String EXCHANGE = "bookingExchange";

    @Bean
    Queue bookingQueue_inValidator() {
        return new Queue(QUEUE, false);
    }

    @Bean
    DirectExchange bookingExchange_inValidator() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    Binding bookingBinding_inValidator(@Qualifier("bookingQueue_inValidator")Queue bookingQueue_inValidator,  @Qualifier("bookingExchange_inValidator") DirectExchange bookingExchange_inValidator) {
        return BindingBuilder.bind(bookingQueue_inValidator).to(bookingExchange_inValidator).with("booking");
    }
    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
