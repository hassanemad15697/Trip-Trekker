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
public class ValidationRabbitMQConfig {

    public static final String QUEUE = "validationQueue";
    public static final String EXCHANGE = "validationExchange";

    @Bean
    Queue validationQueue() {
        return new Queue(QUEUE, false);
    }

    @Bean
    DirectExchange validationExchange() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    Binding validationBinding(@Qualifier("validationQueue")Queue bookingQueue_inValidator,  @Qualifier("validationExchange") DirectExchange bookingExchange_inValidator) {
        return BindingBuilder.bind(bookingQueue_inValidator).to(bookingExchange_inValidator).with("validationKey");
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
