package com.aryansingh.instaforms.utils.email;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("email")
public record EmailConfigurationProperties(String username, String password) {
}
