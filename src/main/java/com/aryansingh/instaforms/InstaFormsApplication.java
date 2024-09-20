package com.aryansingh.instaforms;

import com.aryansingh.instaforms.utils.email.EmailConfigurationProperties;
import com.aryansingh.instaforms.utils.rabbitmq.RabbitMQConfigurationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({EmailConfigurationProperties.class, RabbitMQConfigurationProperties.class})
public class InstaFormsApplication {

    public static void main(String[] args) {
        SpringApplication.run(InstaFormsApplication.class, args);
    }
}
