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
public class OrgDTO {


    @NotEmpty(message = "Organisation name cannot be empty")
    private String organizationName;

    @NotEmpty(message = "Email cannot be empty")
    private String email;

    @NotEmpty(message = "Password is required")
    private String password;

    @NotEmpty(message = "Phone number is mandatory")
    private String phoneNumber;


    private String profileImage;

    @NotEmpty(message = "Require organisation description")
    private String about;

    @NotEmpty(message = "Roles cannot be empty")
    private Set<String> roles = new HashSet<>();


    @JsonIgnore
    public String getPassword(){
        return password;
    }

}
