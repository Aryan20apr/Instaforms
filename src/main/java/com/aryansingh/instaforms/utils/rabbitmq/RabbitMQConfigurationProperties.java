package com.aryansingh.instaforms.utils.rabbitmq;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("rabbit.email")
public record RabbitMQConfigurationProperties(String exchange, String routingKey, String queue) {

}
