package com.aryansingh.instaforms.models.dtos.auth;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDTO {

    @NotEmpty(message = "Username is required")
    private String userName;

    @NotEmpty(message = "Enter a valid password")
    private String password;

}
