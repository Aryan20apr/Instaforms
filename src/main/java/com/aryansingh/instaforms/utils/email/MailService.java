package com.aryansingh.instaforms.utils.email;

import jakarta.mail.MessagingException;

public interface MailService {

    void sendEmail(final AbstractEmailContext email) throws MessagingException;

}
