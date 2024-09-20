package com.aryansingh.instaforms.service.mail.impl;

import com.aryansingh.instaforms.service.mail.MailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

import static com.aryansingh.instaforms.utils.AppConstants.TEMP_SENDER_EMAIL;

@Service
@Slf4j
public class EmailServiceImpl implements MailService {

    private final JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmail(String to, String subject, String message) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        simpleMailMessage.setFrom(TEMP_SENDER_EMAIL);
        log.info("Sending email to :"+to);
        mailSender.send(simpleMailMessage);
    }

    @Override
    public void sendEmail(String[] to, String subject, String message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        simpleMailMessage.setFrom(TEMP_SENDER_EMAIL);
        log.info("Sending bulk emails");
        mailSender.send(simpleMailMessage);
    }

    @Override
    public void sendEmailWithHtml(String to, String subject, String htmlContent) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setFrom(TEMP_SENDER_EMAIL);
            mimeMessageHelper.setText(htmlContent,true);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error("Error sending email: "+e.getMessage());

        }
    }

    @Override
    public void sendEmailWithFile(String to, String subject, String message, String html, File file) {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setFrom(TEMP_SENDER_EMAIL);
            FileSystemResource fileSystemResource = new FileSystemResource(file);
            mimeMessageHelper.addAttachment(Objects.requireNonNull(fileSystemResource.getFilename()),file);
            mimeMessageHelper.setText(html,true);

            log.info("Sending email to :"+to + " with file: "+fileSystemResource.getFilename());
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error("Error sending email: "+e.getMessage());

        }
    }

    @Override
    public void sendEmailWithFile(String to, String subject, String htmlContent, InputStream inputStream) {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {


            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setFrom(TEMP_SENDER_EMAIL);
            File file = new File("sr/main/resources/email/"+UUID.randomUUID().toString());
            Files.copy(inputStream,file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            FileSystemResource fileSystemResource = new FileSystemResource(file);
            mimeMessageHelper.addAttachment(Objects.requireNonNull(fileSystemResource.getFilename()),file);
            mimeMessageHelper.setText(htmlContent,true);

            log.info("Sending email to :"+to + " with file: "+fileSystemResource.getFilename());
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error("Error sending email: "+e.getMessage());

        } catch (IOException e) {
            log.error("Error creating file while sending email: "+e.getMessage());
        }
    }
}
