package com.aryansingh.instaforms.config.security.services;

import com.aryansingh.instaforms.models.entities.security.SecurityOrganisation;
import com.aryansingh.instaforms.models.entities.userAndAuth.Organisation;
import com.aryansingh.instaforms.repositories.organduser.OrganisationRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class OrganisationDetailService implements UserDetailsService {

    private final OrganisationRepository orgUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Organisation> user = orgUserRepository.findByEmail(username);
        return user.map(SecurityOrganisation::new).orElse(null);
    }
}
