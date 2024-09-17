package com.aryansingh.instaforms.config.security.services;

import com.aryansingh.instaforms.models.entities.security.SecurityUser;
import com.aryansingh.instaforms.models.entities.userAndAuth.User;
import com.aryansingh.instaforms.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class SingleUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserNameOrEmail(username);
        return user.map(SecurityUser::new).orElse(null);
    }
}
