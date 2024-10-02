package com.aryansingh.instaforms;

import com.aryansingh.instaforms.config.cloudinary.CloudinaryConfigProperties;
import com.aryansingh.instaforms.utils.email.EmailConfigurationProperties;
import com.aryansingh.instaforms.utils.rabbitmq.DeadLetterQueueConfigProperties;
import com.aryansingh.instaforms.utils.rabbitmq.RabbitMQConfigurationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@SpringBootApplication
@EnableAsync
@EnableConfigurationProperties(
        {EmailConfigurationProperties.class,
                RabbitMQConfigurationProperties.class,
                DeadLetterQueueConfigProperties.class,
                CloudinaryConfigProperties.class})
public class InstaFormsApplication {

    public static void main(String[] args) {
        SpringApplication.run(InstaFormsApplication.class, args);
    }

    @Bean(name = "asyncExecutor")
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        // Set the core pool size
        executor.setCorePoolSize(5); // Minimum number of threads
        executor.setMaxPoolSize(10);  // Maximum number of threads
        executor.setQueueCapacity(100); // Queue capacity for holding tasks before they are executed

        // Set the thread name prefix for easier debugging
        executor.setThreadNamePrefix("AsyncExecutor-");

        // Initialize the executor
        executor.initialize();
        return executor;
    }
}
