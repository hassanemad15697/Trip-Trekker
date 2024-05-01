package com.mentor.triptrekker.validation.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CorrelationData;
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

    public static final String VALIDATION_QUEUE = "validationQueue";
    public static final String VALIDATION_EXCHANGE = "validationExchange";
    public static final String VALIDATION_KEY = "validationKey";

    @Bean
    Queue validationQueue() {
        return new Queue(VALIDATION_QUEUE, true, false, false);
    }

    @Bean
    DirectExchange validationExchange() {
        return new DirectExchange(VALIDATION_EXCHANGE);
    }

    @Bean
    Binding validationBinding(@Qualifier("validationQueue")Queue validationQueue,  @Qualifier("validationExchange") DirectExchange validationExchange) {
        return BindingBuilder.bind(validationQueue).to(validationExchange).with(VALIDATION_KEY);
    }
    @Bean
    public RabbitTemplate validationRabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(validationMessageConverter());
        rabbitTemplate.setConfirmCallback((CorrelationData correlationData, boolean ack, String cause) -> {
            if (!ack) {
                System.out.println("Message with CorrelationData " + correlationData + " failed with cause " + cause);
            }
        });
        System.out.println("validationRabbitTemplate");
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter validationMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
