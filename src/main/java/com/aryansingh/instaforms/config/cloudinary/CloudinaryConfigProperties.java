package com.aryansingh.instaforms.config.cloudinary;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "cloudinary")
public record CloudinaryConfigProperties(String cloudName,String apiKey,String apiSecret) {

}
