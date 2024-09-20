package com.aryansingh.instaforms.utils.rabbitmq;

import com.aryansingh.instaforms.models.dtos.mail.SimpleMailDTO;
import com.aryansingh.instaforms.utils.email.AccountVerificationEmailContext;
import com.aryansingh.instaforms.utils.email.MailService;
import com.aryansingh.instaforms.utils.exceptions.ApiException;
import com.rabbitmq.client.Channel;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@AllArgsConstructor
@Slf4j
public class EmailConsumer {

    private final MailService mailService;


    @RabbitListener(queues = "${rabbit.email.queue}", messageConverter = "jsonMessageConverter",containerFactory = "rabbitListenerContainerFactory")
    public void consumeEmailReuquest(SimpleMailDTO simpleMailDTO, Channel channel, Message message){
        log.info("Consuming email request: {}", simpleMailDTO);
        AccountVerificationEmailContext accountVerificationEmailContext =  new AccountVerificationEmailContext();
        accountVerificationEmailContext.init();
        accountVerificationEmailContext.setTo(simpleMailDTO.getTo());
        accountVerificationEmailContext.setSubject(simpleMailDTO.getSubject());
        accountVerificationEmailContext.setOTP(simpleMailDTO.getMessage());
       try {
           try {
               mailService.sendEmail(accountVerificationEmailContext);
               channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
           } catch (MessagingException e) {
               log.error("Error sending email"+e.getMessage());
               throw new ApiException("Error sending email");
           } catch (Exception e) {
               channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
           }
       } catch (IOException e) {
           log.error("Error sending email "+e.getMessage());
           throw new ApiException("Error sending email");
       }

    }
}
