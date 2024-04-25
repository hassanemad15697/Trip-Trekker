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
public class PaymentRabbitMQConfig {

    public static final String PAYMENT_QUEUE = "paymentQueue";
    public static final String PAYMENT_EXCHANGE = "paymentExchange";

    @Bean
    Queue paymentQueue() {
        return new Queue(PAYMENT_QUEUE, false);
    }

    @Bean
    DirectExchange paymentExchange() {
        return new DirectExchange(PAYMENT_EXCHANGE);
    }

    @Bean
    Binding paymentBindings(@Qualifier("paymentQueue") Queue paymentQueue, @Qualifier("paymentExchange") DirectExchange paymentExchange) {
        return BindingBuilder.bind(paymentQueue).to(paymentExchange).with("paymentKey");
    }

    @Bean
    public RabbitTemplate bookingRabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(paymentMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter paymentMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}