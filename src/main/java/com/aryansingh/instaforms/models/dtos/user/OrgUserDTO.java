package com.aryansingh.instaforms.models.dtos.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OrgUserDTO {

    @NotEmpty(message = "First name must not be empty")
    private String firstName;

    @NotEmpty(message = "Last name must not be empty")
    private String lastName;

    @NotEmpty(message = "Email must not be empty")
    private String email;

    @NotEmpty(message = "Password must not be empty")
    private String password;

    @NotEmpty(message = "Phone number must not be empty")
    private String phoneNumber;

    private String profileImage;

    @NotEmpty(message = "Roles must not be empty")
    private Set<String> roles = new HashSet<>();

    @JsonIgnore
    public String getPassword() {
        return password;
    }
}
