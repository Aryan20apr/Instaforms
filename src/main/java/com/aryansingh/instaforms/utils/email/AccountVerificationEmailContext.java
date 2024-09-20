package com.aryansingh.instaforms.utils.email;

import com.aryansingh.instaforms.utils.AppConstants;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public  class AccountVerificationEmailContext extends AbstractEmailContext {

    private String token;

    @Override
    public  void init() {

        setTemplateLocation("emails/email-verification.html");
        setSubject("Verify your email and complete your registration");
        setFrom(AppConstants.TEMP_SENDER_EMAIL);
        setFromDisplayName("No-reply@instaforms");
    }

    public void setOTP(String otp){
        put("verificationOTP",otp);
    }


}
