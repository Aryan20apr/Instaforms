package com.aryansingh.instaforms.utils.rabbitmq;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("rabbit.dlx")
public record DeadLetterQueueConfigProperties(String exchange, String routingKey, String queue) {

}
