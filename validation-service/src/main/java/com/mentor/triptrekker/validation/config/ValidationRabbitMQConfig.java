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

    public static final String QUEUE = "validationQueue";
    public static final String EXCHANGE = "validationExchange";
    public static final String KEY = "validationKey";

    @Bean
    Queue bookingQueue() {
        return new Queue(QUEUE, false);
    }

    @Bean
    DirectExchange bookingExchange() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    Binding bookingBinding(@Qualifier("bookingQueue")Queue bookingQueue,  @Qualifier("bookingExchange") DirectExchange bookingExchange) {
        return BindingBuilder.bind(bookingQueue).to(bookingExchange).with(KEY);
    }
    @Bean
    public RabbitTemplate validationRabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(validationMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter validationMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
