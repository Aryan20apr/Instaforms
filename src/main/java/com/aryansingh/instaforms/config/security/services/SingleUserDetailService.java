package com.aryansingh.instaforms.config.security.services;

import com.aryansingh.instaforms.models.entities.security.SecurityUser;
import com.aryansingh.instaforms.models.entities.userAndAuth.SingleUser;
import com.aryansingh.instaforms.repositories.organduser.UserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class SingleUserDetailService implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(SingleUserDetailService.class);
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Username: " + username);
        Optional<SingleUser> user = userRepository.findByUserNameOrEmail(username,username);
        log.info("Username: " + username+" "+user.isPresent());
        return user.map(SecurityUser::new).orElse(null);
    }
}
