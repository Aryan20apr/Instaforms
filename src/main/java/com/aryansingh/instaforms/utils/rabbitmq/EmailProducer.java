package com.aryansingh.instaforms.utils.rabbitmq;

import com.aryansingh.instaforms.models.dtos.mail.SimpleMailDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbit.email.exchange}")
    public String exchange;

    @Value("${rabbit.email.routing-key}")
    public String emailRoutingKey;

    public EmailProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    public void sendToQueue(SimpleMailDTO simpleMailDTO) {
        rabbitTemplate.convertAndSend(exchange, emailRoutingKey, simpleMailDTO);
        log.info("Email request sent to queue: {}", simpleMailDTO);
    }


}
