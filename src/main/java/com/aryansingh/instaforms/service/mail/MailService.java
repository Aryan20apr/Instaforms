package com.aryansingh.instaforms.service.mail;

import java.io.File;
import java.io.InputStream;

public interface MailService {

    void sendEmail(String to, String subject, String message);

    void sendEmail(String []to, String subject, String message);

    void sendEmailWithHtml(String to, String subject, String htmlContent);

    void sendEmailWithFile(String to, String subject, String message, String html, File file);

    void sendEmailWithFile(String to, String subject, String htmlContent, InputStream inputStream);
}
