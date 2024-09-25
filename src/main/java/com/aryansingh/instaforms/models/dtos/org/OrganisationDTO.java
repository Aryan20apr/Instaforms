package com.aryansingh.instaforms.models.dtos.org;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Builder
@AllArgsConstructor
public class OrganisationDTO {

    @NotBlank(message = "Organization name cannot be blank")
    @Size(min = 3, max = 100, message = "Organization name must be between 3 and 100 characters")
    private String organizationName;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(regexp = "\\d{10}", message = "Phone number must be a valid 10-digit number")
    private String phoneNumber;

    private String profileImage;

    @NotBlank(message = "About information cannot be blank")
    @Size(max = 1000, message = "About section must not exceed 1000 characters")
    private String about;

    private Set<String> roles;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "Password cannot be blank")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

}
