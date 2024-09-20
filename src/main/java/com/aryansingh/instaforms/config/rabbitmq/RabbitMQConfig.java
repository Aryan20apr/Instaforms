//package com.aryansingh.instaforms.config.rabbitmq;
//
//import org.springframework.amqp.core.*;
//import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder;
//import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
//import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
//import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class RabbitMQConfig {
//
//    @Value("${rabbit.email.queue}")
//    public String emailQueue;
//
//    @Value("${rabbit.email.exchange}")
//    public String exchange;
//
//    @Value("${rabbit.email.routing-key}")
//    public String emailRoutingKey;
//
//    @Value("${rabbit.dlx.exchange}")
//    public String dlxExchange;
//
//    @Value("${rabbit.dlx.queue}")
//    public String dlxQueue;
//
//    @Value("${rabbit.dlx.routing-key}")
//    public String dlxRoutingKey;
//
//    // Email Queue
//    @Bean
//    public Queue emailQueue() {
//        return QueueBuilder.durable(emailQueue)
//                .withArgument("x-dead-letter-exchange", dlxExchange)  // Dead Letter Exchange
//                .withArgument("x-dead-letter-routing-key", dlxRoutingKey)  // Dead Letter Routing Key
//                .build();
//    }
//
//    // Direct Exchange for Email
//    @Bean
//    public DirectExchange emailExchange() {
//        return new DirectExchange(exchange);
//    }
//
//    // Dead Letter Exchange
//    @Bean
//    public DirectExchange deadLetterExchange() {
//        return new DirectExchange(dlxExchange);
//    }
//
//    // Dead Letter Queue
//    @Bean
//    public Queue deadLetterQueue() {
//        return QueueBuilder.durable(dlxQueue).build();
//    }
//
//    // Binding for Email Queue to the Email Exchange
//    @Bean
//    public Binding emailBinding(Queue emailQueue, DirectExchange emailExchange) {
//        return BindingBuilder.bind(emailQueue).to(emailExchange).with(emailRoutingKey);
//    }
//
//    // Binding for Dead Letter Queue to the Dead Letter Exchange
//    @Bean
//    public Binding deadLetterBinding(Queue deadLetterQueue, DirectExchange deadLetterExchange) {
//        return BindingBuilder.bind(deadLetterQueue).to(deadLetterExchange).with(dlxRoutingKey);
//    }
//
//    // RabbitTemplate configuration for message sending
//    @Bean
//    public RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactory) {
//        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
//        rabbitTemplate.setMessageConverter(jsonMessageConverter());
//        return rabbitTemplate;
//    }
//
//    // JSON message converter for RabbitTemplate
//    @Bean
//    public Jackson2JsonMessageConverter jsonMessageConverter() {
//        return new Jackson2JsonMessageConverter();
//    }
//
//    // Listener container factory with retry and concurrency configurations
//    @Bean
//    public RabbitListenerContainerFactory<SimpleMessageListenerContainer> rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory);
//        factory.setConcurrentConsumers(1); // Minimum number of concurrent consumers
//        factory.setMaxConcurrentConsumers(5); // Maximum number of concurrent consumers
//        factory.setDefaultRequeueRejected(false); // Don't requeue after rejection
//        factory.setAdviceChain(RetryInterceptorBuilder.stateless()
//                .maxAttempts(3) // Max retry attempts before DLQ
//                .build());
//        return factory;
//    }
//}
package com.aryansingh.instaforms.config.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbit.email.queue}")
    public String emailQueue;

    @Value("${rabbit.email.exchange}")
    public String exchange;

    @Value("${rabbit.email.routing-key}")
    public String emailRoutingKey;

    @Value("${rabbit.dlx.exchange}")
    public String dlxExchange;

    @Value("${rabbit.dlx.queue}")
    public String dlxQueue;

    @Value("${rabbit.dlx.routing-key}")
    public String dlxRoutingKey;

    // Email Queue
    @Bean
    public Queue emailQueue() {
        return QueueBuilder.durable(emailQueue)
                .withArgument("x-dead-letter-exchange", dlxExchange)  // Dead Letter Exchange
                .withArgument("x-dead-letter-routing-key", dlxRoutingKey)  // Dead Letter Routing Key
                .build();
    }

    // Direct Exchange for Email
    @Bean
    public DirectExchange emailExchange() {
        return new DirectExchange(exchange);
    }

    // Dead Letter Exchange
    @Bean
    public DirectExchange deadLetterExchange() {
        return new DirectExchange(dlxExchange);
    }

    // Dead Letter Queue
    @Bean
    public Queue deadLetterQueue() {
        return QueueBuilder.durable(dlxQueue).build();
    }

    // Binding for Email Queue to the Email Exchange
    @Bean
    public Binding emailBinding(Queue emailQueue, DirectExchange emailExchange) {
        return BindingBuilder.bind(emailQueue).to(emailExchange).with(emailRoutingKey);
    }

    // Binding for Dead Letter Queue to the Dead Letter Exchange
    @Bean
    public Binding deadLetterBinding(Queue deadLetterQueue, DirectExchange deadLetterExchange) {
        return BindingBuilder.bind(deadLetterQueue).to(deadLetterExchange).with(dlxRoutingKey);
    }

    // RabbitTemplate configuration for message sending
    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

    // JSON message converter for RabbitTemplate
    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    // Listener container factory with retry and concurrency configurations
    @Bean
    public RabbitListenerContainerFactory<SimpleMessageListenerContainer> rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setConcurrentConsumers(1); // Minimum number of concurrent consumers
        factory.setMaxConcurrentConsumers(5); // Maximum number of concurrent consumers
        factory.setDefaultRequeueRejected(false); // Don't requeue after rejection
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        factory.setAdviceChain(RetryInterceptorBuilder.stateless()
                .maxAttempts(3) // Max retry attempts before DLQ
                .build());
        return factory;
    }
}
