package com.aryansingh.instaforms.config.security.authprovider;

import com.aryansingh.instaforms.config.security.services.SingleUserDetailService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserAuthProvider implements AuthenticationProvider {

    private final SingleUserDetailService userDetailService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = String.valueOf(authentication.getPrincipal());
        String password=String.valueOf(authentication.getCredentials());

        UserDetails userDetails = userDetailService.loadUserByUsername(username);
        if(userDetails!=null && passwordEncoder.matches(password,userDetails.getPassword())){
                if(userDetails.isEnabled()){
                    return new UsernamePasswordAuthenticationToken(username,password,userDetails.getAuthorities());
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
