package com.aryansingh.instaforms.config.security.authprovider;

import com.aryansingh.instaforms.config.security.services.OrgUserDetailService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrgUserAuthProvider implements AuthenticationProvider
{

    private final OrgUserDetailService orgUserDetailService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = String.valueOf(authentication.getPrincipal());
        String password=String.valueOf(authentication.getCredentials());

        UserDetails orgUserDetails = orgUserDetailService.loadUserByUsername(email);
        if(orgUserDetails!=null && passwordEncoder.matches(password,orgUserDetails.getPassword())){
            if(orgUserDetails.isEnabled()){
                return new UsernamePasswordAuthenticationToken(email,password,orgUserDetails.getAuthorities());
            }
            else{
                throw new DisabledException("Account has not been verified");
            }
        }
        throw new BadCredentialsException("Wrong Credentials");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}
