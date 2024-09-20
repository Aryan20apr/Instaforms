package com.aryansingh.instaforms.models.dtos.mail;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class SimpleMailDTO {

    @NotNull(message = "Recipient Id cannot be null.")
    private String to;

    @NotNull(message = "Subject cannot be empty.")
    private String subject;

    @NotNull(message = "Message cannot be empty.")
    private String message;

}
