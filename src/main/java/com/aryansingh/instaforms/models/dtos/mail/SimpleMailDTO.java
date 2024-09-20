package com.aryansingh.instaforms.models.dtos.mail;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@Setter
@Getter
@ToString
public class SimpleMailDTO implements Serializable {

    @NotNull(message = "Recipient Id cannot be null.")
    private String to;

    @NotNull(message = "Subject cannot be empty.")
    private String subject;

    @NotNull(message = "Message cannot be empty.")
    private String message;

}
