package com.aryansingh.instaforms.models.entities.security;

import com.aryansingh.instaforms.models.entities.userAndAuth.OrganisationUser;
import com.aryansingh.instaforms.models.entities.userAndAuth.Privilege;
import com.aryansingh.instaforms.models.entities.userAndAuth.Role;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@AllArgsConstructor
    public class SecurityOrgUser implements UserDetails {

    private final OrganisationUser organisationUser;

    private List<String> getPrivileges(Collection<Role> roles) {

        List<String> privileges = new ArrayList<>();
        Set<Privilege> collection = new HashSet<>();
        for (Role role : roles) {
            privileges.add(role.getRoleName());
            privileges.addAll(role.getPrivileges().stream().map(Privilege::getPrivilegeName).toList());
        }
        //        for (Privilege item : collection) {
        //            privileges.add(item.getName());
        //        }
        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getGrantedAuthorities(getPrivileges((organisationUser.getRoles())));
    }

    @Override
    public String getPassword() {
        return organisationUser.getPassword();
    }

    @Override
    public String getUsername() {
        return organisationUser.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}

