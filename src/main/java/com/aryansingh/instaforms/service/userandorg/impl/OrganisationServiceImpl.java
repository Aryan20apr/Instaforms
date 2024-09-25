package com.aryansingh.instaforms.service.userandorg.impl;

import com.aryansingh.instaforms.models.dtos.auth.LoginRequestDTO;
import com.aryansingh.instaforms.models.dtos.auth.TokenDTO;
import com.aryansingh.instaforms.models.dtos.org.OrganisationDTO;
import com.aryansingh.instaforms.models.entities.userAndAuth.Organisation;
import com.aryansingh.instaforms.models.entities.userAndAuth.Role;
import com.aryansingh.instaforms.repositories.organduser.OrganisationRepository;
import com.aryansingh.instaforms.service.role.RoleService;
import com.aryansingh.instaforms.service.userandorg.OrganisationService;
import com.aryansingh.instaforms.utils.AppConstants;
import com.aryansingh.instaforms.utils.exceptions.ApiException;
import com.aryansingh.instaforms.utils.security.JWTUtils;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@AllArgsConstructor
@Service
@Slf4j
public class OrganisationServiceImpl implements OrganisationService {

    private OrganisationRepository organisationRepository;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;
    
    @Override
    public boolean registerOrganisation(OrganisationDTO organisationDTO) {
        String email = organisationDTO.getEmail();


        Optional<Organisation> organisation = organisationRepository.findByEmail(email);

        if(organisation.isEmpty()){
            Organisation newOrg= Organisation.builder()
                    .organizationName(organisationDTO.getOrganizationName())
                    .about(organisationDTO.getAbout())
                    .email(organisationDTO.getEmail())
                    .phoneNumber(organisationDTO.getPhoneNumber())
                    .profileImage(organisationDTO.getProfileImage())
                    .build();

            // TODO: Add phone number and email verification


            Set<Role> roles = new HashSet<>();

            organisationDTO.getRoles().forEach(roleName->{
                Role role =roleService.findByRoleName(roleName);
                roles.add(role);
            });
            newOrg.setRoles(roles);

            newOrg.setPassword(passwordEncoder.encode(organisationDTO.getPassword()));

           organisationRepository.save(newOrg);
        } else{
            throw new ApiException("User with the email or username already exists");
        }
        return true;
    }

    @Override
    @Transactional
    public OrganisationDTO getOrganisationDetails() {
        String orgToken = JWTUtils.extract(AppConstants.UNIQUE_TOKEN);
        log.info("Obtained user token: "+orgToken);
        Optional<Organisation> organisation = organisationRepository.findByOrganisationToken(orgToken);

        if(organisation.isEmpty()){
            throw new ApiException("organisation not found");
        } else {
            OrganisationDTO organisationDTO = OrganisationDTO.builder()
                    .organizationName(organisation.get().getOrganizationName())
                    .about(organisation.get().getAbout())
                    .email(organisation.get().getEmail())
                    .phoneNumber(organisation.get().getPhoneNumber())
                    .profileImage(organisation.get().getProfileImage())
                    .build();

            Set<Role> eroles= organisation.get().getRoles();
            Set<String> roles = new HashSet<>();
            eroles.forEach(role->{roles.add(role.getRoleName());});
            organisationDTO.setRoles(roles);
            return
                    organisationDTO;
        }
    }

    @Override
    public TokenDTO login(LoginRequestDTO loginRequestDTO) {
        log.info("Login request : {}", loginRequestDTO);
        if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
            String email = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Optional<Organisation> organisation = organisationRepository.findByEmail(email);
            if(organisation.isEmpty()){
                throw new ApiException("User not found with provided username or email.");
            }
            String jwt =  JWTUtils.generateToken(organisation.get().getEmail(),AppConstants.ENTITY_TYPE_ORGANISATION ,organisation.get().getOrganisationToken());
            return TokenDTO.builder().token(jwt).build();

            // TODO: Add refresh token also
        } else{
            throw new ApiException("User not authenticated");
        }
    }
}
