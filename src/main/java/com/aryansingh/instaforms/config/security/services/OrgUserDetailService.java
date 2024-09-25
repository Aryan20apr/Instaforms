package com.aryansingh.instaforms.config.security.services;

import com.aryansingh.instaforms.models.entities.security.SecurityOrgUser;
import com.aryansingh.instaforms.models.entities.userAndAuth.OrganisationUser;
import com.aryansingh.instaforms.repositories.organduser.OrgUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class OrgUserDetailService implements UserDetailsService {

    private final OrgUserRepository orgUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<OrganisationUser> user = orgUserRepository.findByEmail(username);
        return user.map(SecurityOrgUser::new).orElse(null);
    }
}
